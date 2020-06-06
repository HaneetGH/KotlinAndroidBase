package com.technorapper.boiler.ui.onboarding.activity

import android.app.Application
import android.content.Context
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import com.technorapper.boiler.application.App
import com.technorapper.boiler.data.tunnel.remote.utils.AppSocket
import com.technorapper.boiler.data.tunnel.remote.utils.CheckInternetConnection
import com.technorapper.boiler.di.module.AppModule
import dagger.Module
import io.mockk.mockk
import okhttp3.OkHttpClient
class TestApplicationModule(app: App) : AppModule(app) {

    override fun picasso(app: App): Picasso = mockk()
    override fun okHttp3Downloader(okHttpClient: OkHttpClient?): OkHttp3Downloader? = mockk()
    override fun context(): Context = mockk()
    override fun provideExampleSocket(app: App): AppSocket = mockk()
    override fun provideInternetConnection(application: Context): CheckInternetConnection? = mockk()
    override fun provideApplication(): Application = mockk()

}