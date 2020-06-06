package com.technorapper.boiler.di.module

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.technorapper.boiler.data.tunnel.remote.SynchronousApi
import com.technorapper.boiler.data.tunnel.remote.utils.CheckInternetConnection
import com.technorapper.boiler.di.scopes.ApplicationScoped
import com.technorapper.boiler.helpers.AppPrefs
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.net.CookieHandler
import java.net.CookieManager
import java.net.CookiePolicy
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
class NetworkModule {
    companion object {

        private const val TIMEOUT_IN_MS = 30000
        private const val LT_BASE_URL = "lt_base_url"
        private const val TOKEN = "token"

        private val BASE_URL = "https://api.github.com/"
    }

    @Provides
    @ApplicationScoped
    fun provideAuthorizationInterceptor(
        @Named(TOKEN) token: String, connection: CheckInternetConnection
    ): Interceptor {
        return Interceptor { chain ->


            var request = chain.request()

            val requestBuilder = request.newBuilder()
            //  .addHeader("Authorization", "Bearer " + token);
            val cacheControl = CacheControl.Builder()
                .maxAge(40, TimeUnit.SECONDS)
                .build()

            if (connection.isNetworkAvailable()) {
                val maxAge = 20 // read from cache for 1 minute
                //request.newBuilder()
                requestBuilder
                    // .addHeader("Authorization", "Bearer$token")

                    .addHeader(
                        "Authorization",
                        "Bearer $token"
                    )
                    .addHeader("Content-Type", "application/json")
                    .header("Cache-Control", "public, max-age=$maxAge")
                    .cacheControl(cacheControl)
                    .build()
            } else {
                val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
                // request.newBuilder()
                requestBuilder
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .build()
            }

            request = requestBuilder.build()

            val response = chain.proceed(request)
            if (response.code() == 401) {

                // Expire Token
                return@Interceptor response
            }
            response
        }
    }

    @Provides
    @Named(LT_BASE_URL)
    fun provideBaseUrlString(): String {
        return BASE_URL

    }


    @Provides
    @ApplicationScoped
    internal fun provideRetrofitInterface(
        okHttpClient: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())

            .build()

    }


    @Provides
    @Named(TOKEN)
    fun provideToken(preference: AppPrefs): String? {
        return preference.TOKEN
    }

    @RequiresApi(Build.VERSION_CODES.GINGERBREAD)
    @Provides
    @ApplicationScoped
    fun provideCookieManager(): CookieManager? {
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)
        CookieHandler.setDefault(cookieManager)
        return cookieManager
    }

    @Provides
    @ApplicationScoped
    fun provideLoggingInterceptor(): HttpLoggingInterceptor? {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @ApplicationScoped
    fun provideOKHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: Interceptor,
        cookieJar: CookieJar,
        cache: Cache
    ): OkHttpClient {

        // Create a trust manager that does not validate certificate chains
        val trustAllCerts =
            arrayOf<TrustManager>(
                object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate>,
                        authType: String
                    ) {
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(
                        chain: Array<X509Certificate>,
                        authType: String
                    ) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate> {
                        return arrayOf()
                    }
                }
            )

        // Install the all-trusting trust manager

        // Install the all-trusting trust manager
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        // Create an ssl socket factory with our all-trusting manager

        // Create an ssl socket factory with our all-trusting manager
        val sslSocketFactory = sslContext.socketFactory

        val builder = OkHttpClient.Builder()
        builder.sslSocketFactory(
            sslSocketFactory,
            trustAllCerts[0] as X509TrustManager
        )
        builder.hostnameVerifier { hostname, session -> true }
        return OkHttpClient.Builder()

            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES) // write timeout
            .readTimeout(1, TimeUnit.MINUTES) // read timeout
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .cookieJar(cookieJar)
            .cache(cache)
            .build()

    }

    @Provides
    @ApplicationScoped
    fun provideMoshi(): MoshiConverterFactory {
        return MoshiConverterFactory.create(Moshi.Builder().add(KotlinJsonAdapterFactory()).build())
    }

    @Provides
    @ApplicationScoped
    fun provideRxAdapter(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory
            .createWithScheduler(Schedulers.io())
    }

    @Provides
    @ApplicationScoped
    fun provideexampleService(@Named(LT_BASE_URL) baseUrl: String): SynchronousApi {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(ScalarsConverterFactory.create())
            .build().create(SynchronousApi::class.java)
    }

    @Provides
    @ApplicationScoped
    fun provideGson(): Gson? {
        return GsonBuilder() /* .registerTypeAdapterFactory(tvMazeTypeAdaptorFactory)*/
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setLenient()
            .create()
    }

    @Provides
    @ApplicationScoped
    fun provideCookieJar(context: Application?): CookieJar? {
        return PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context))
    }


    @Provides
    @ApplicationScoped
    fun provideCache(context: Application): Cache? {
        val cacheSize = 5 * 1024 * 1024 // 5 MB
        val cacheDir = context.cacheDir
        return Cache(cacheDir, cacheSize.toLong())
    }

    @Provides
    @ApplicationScoped
    fun provideRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory? {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    }
}



