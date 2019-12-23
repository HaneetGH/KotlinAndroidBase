package com.bt.whide.driver.di.module

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.bt.whide.driver.constants.GlobalVariables
import com.bt.whide.driver.data.tunnel.remote.SynchronousApi
import com.bt.whide.driver.di.scopes.ApplicationScoped
import com.bt.whide.driver.helpers.AppPrefs
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.CookieJar
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.CookieHandler
import java.net.CookieManager
import java.net.CookiePolicy
import javax.inject.Named

@Module
class NetworkModule {
    companion object {

        private const val TIMEOUT_IN_MS = 30000
        private const val LT_BASE_URL = "lt_base_url"
        private const val TOKEN = "token"
        private val BASE_URL = "http://apicustomer.whide.com/api/"
    }

    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url()
            .newBuilder()
            /* .addQueryParameter("api_key", GlobalVariables.TIME_STAMP_FORMAT)*/
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
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
    fun okhttpclinet(): OkHttpClient? {
        return OkHttpClient().newBuilder()
            .addInterceptor(authInterceptor)
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
    fun provideWhideService(@Named(LT_BASE_URL) baseUrl: String): SynchronousApi {
        return Retrofit.Builder().baseUrl(baseUrl) .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())).build().create(SynchronousApi::class.java)
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



