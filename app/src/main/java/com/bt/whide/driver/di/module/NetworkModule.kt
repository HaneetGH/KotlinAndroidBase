package com.bt.whide.driver.di.module

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.bt.whide.driver.data.tunnel.remote.utils.CheckInternetConnection
import com.bt.whide.driver.di.scopes.ApplicationScoped
import com.bt.whide.driver.helpers.AppPrefs
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.net.CookieHandler
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
class NetworkModule {
    companion object {

        private const val TIMEOUT_IN_MS = 30000
        private const val LT_BASE_URL = "lt_base_url"
        private const val TOKEN = "token"
        private val BASE_URL = "http://apicustomer.whide.com/api/"
    }


    @Provides
    @Named(LT_BASE_URL)
    fun provideBaseUrlString(preference: AppPrefs?): String? {
        return BASE_URL
        //return preference.getBaseUrl();
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

  /*  @Provides
    @ApplicationScoped
    fun provideHeaderAuthorizationInterceptor(
        @Named(TOKEN) token: String, connection: CheckInternetConnection
    ): Interceptor? { //  final String authToken = Credentials.basic("Letstrack", "LTS@NowInIndia");
        return label@ Interceptor { chain: Interceptor.Chain ->
            *//*  Request request = chain.request();
                Request.Builder requestBuilder = request.newBuilder()
                        .addHeader("Authorization", "Bearer " + token);

                request = requestBuilder.build();
                Response response = chain.proceed(request);



                if (response.code() == 401) {

                    return response;
                }*//*
            var request = chain.request()
            val requestBuilder = request.newBuilder()
            //  .addHeader("Authorization", "Bearer " + token);
            val cacheControl = CacheControl.Builder()
                .maxAge(40, TimeUnit.SECONDS)
                .build()
            if (true) {
                val maxAge = 20 // read from cache for 1 minute
                request.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .header("Cache-Control", "public, max-age=$maxAge")
                    .cacheControl(cacheControl)
                    .build()
            } else {
                val maxStale = 60 * 60 * 24 * 28 // tolerate 4-weeks stale
                request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .build()
            }
            request = requestBuilder.build()
            val response = chain.proceed(request)
            if (response.code() == 401) {
                return@label response
            }
            response
        }
    }*/


    /*@Provides
    @ApplicationScoped
    fun provideOkHttpClient(
        cookieJar: CookieJar?,
        loggingInterceptor: HttpLoggingInterceptor?,
        headerAuthorizationInterceptor: Interceptor?,
        cache: Cache?
    ): OkHttpClient? {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headerAuthorizationInterceptor)
            .connectTimeout(
                TIMEOUT_IN_MS.toLong(),
                TimeUnit.MILLISECONDS
            )
            .cookieJar(cookieJar)
            .cache(cache)
            .build()
    }
*/
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



