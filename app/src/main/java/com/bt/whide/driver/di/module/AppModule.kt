package com.bt.whide.driver.di.module

import android.app.Application
import android.content.Context
import com.bt.whide.driver.application.App
import com.bt.whide.driver.constants.GlobalVariables
import com.bt.whide.driver.data.tunnel.remote.utils.AppSocket
import com.bt.whide.driver.di.AppSocketServiceFactory
import com.bt.whide.driver.di.scopes.ApplicationScoped
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import java.net.URISyntaxException

@Module(includes = arrayOf(ViewModelModule::class))
class AppModule(val application: App) {


    @Provides
    @ApplicationScoped
    fun picasso(
        app: App
    ): Picasso {
        return Picasso.Builder(app.applicationContext).loggingEnabled(true).build()
    }

    @Provides
    fun okHttp3Downloader(okHttpClient: OkHttpClient?): OkHttp3Downloader? {
        return OkHttp3Downloader(okHttpClient)
    }


    @Provides
    @ApplicationScoped
    fun context(): Context {
        return application
    }
    @Provides
    @ApplicationScoped
    fun provideExampleSocket(app: App): AppSocket {
        return AppSocketServiceFactory.whideSocket()
    }




}