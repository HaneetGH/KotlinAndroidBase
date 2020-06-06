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
import com.technorapper.boiler.data.tunnel.remote.utils.CheckInternetConnection
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import java.net.URISyntaxException

@Module(includes = [ViewModelModule::class])
open class AppModule(val application: App) {


    @Provides
    @ApplicationScoped
    open fun picasso(
        app: App
    ): Picasso {
        return Picasso.Builder(app.applicationContext).loggingEnabled(true).build()
    }

    @Provides
    open fun okHttp3Downloader(okHttpClient: OkHttpClient?): OkHttp3Downloader? {
        return OkHttp3Downloader(okHttpClient)
    }


    @Provides
    @ApplicationScoped
    open fun context(): Context {
        return application
    }

    @Provides
    @ApplicationScoped
    open fun provideExampleSocket(app: App): AppSocket {
        return AppSocketServiceFactory.exampleSocket()
    }

    @Provides
    @ApplicationScoped
    open fun provideInternetConnection(application: Context): CheckInternetConnection? {
        return CheckInternetConnection(application)
    }

    @Provides
    @ApplicationScoped
    open fun provideApplication(): Application {
        return application
    }




}