package com.technorapper.boiler.di.module

import android.app.Application
import android.content.Context

import com.technorapper.boiler.constants.GlobalVariables
import com.technorapper.boiler.data.tunnel.remote.utils.AppSocket
import com.technorapper.boiler.di.AppSocketServiceFactory
import com.technorapper.boiler.di.scopes.ApplicationScoped
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import com.technorapper.boiler.application.App
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
        return AppSocketServiceFactory.exampleSocket()
    }




}