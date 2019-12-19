package com.bt.whide.driver.di.module

import android.app.Application
import android.content.Context
import com.bt.whide.driver.application.App
import com.bt.whide.driver.di.scopes.ApplicationScoped
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Module(includes = arrayOf(ViewModelModule::class))
class AppModule(private var application: Application?) {


    @Provides
    @ApplicationScoped
    fun provideApplication(): Application? {
        return application
    }

    @Provides
    @ApplicationScoped
    fun picasso(app: Application?): Picasso? {
        return Picasso.Builder(app!!.applicationContext)
            .loggingEnabled(true)
            .build()
    }

    @Provides
    @ApplicationScoped
    fun provideContext(): Context? {
        return application
    }
    @Provides
    @ApplicationScoped
    fun okHttp3Downloader(okHttpClient: OkHttpClient?): OkHttp3Downloader? {
        return OkHttp3Downloader(okHttpClient)
    }

}