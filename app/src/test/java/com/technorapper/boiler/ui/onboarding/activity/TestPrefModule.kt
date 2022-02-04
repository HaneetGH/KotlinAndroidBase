package com.technorapper.boiler.ui.onboarding.activity

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import com.technorapper.boiler.application.App
import com.technorapper.boiler.data.tunnel.remote.utils.AppSocket
import com.technorapper.boiler.data.tunnel.remote.utils.CheckInternetConnection
import com.technorapper.boiler.di.module.AppModule
import com.technorapper.boiler.di.module.PreferenceModule
import com.technorapper.boiler.helpers.AppPrefs
import dagger.Module
import io.mockk.mockk
import okhttp3.OkHttpClient
class TestPrefModule() : PreferenceModule()
{

    override fun provideTechnorapperPreference(preferences: SharedPreferences?): AppPrefs = mockk()

    override fun providePreferences(context: App): SharedPreferences = mockk()
}