package com.bt.whide.driver.di.module

import android.content.Context
import android.content.SharedPreferences
import com.bt.whide.driver.constants.Preferences
import com.bt.whide.driver.application.App
import com.bt.whide.driver.di.scopes.ApplicationScoped

import com.bt.whide.driver.helpers.AppPrefs
import dagger.Module
import dagger.Provides

@Module
 class PreferenceModule {

    @Provides
    @ApplicationScoped
    fun provideLetstrackPreference(preferences: SharedPreferences?): AppPrefs {
        return AppPrefs(preferences)
    }
    
    @Provides
    @ApplicationScoped
    fun providePreferences(context: App): SharedPreferences {
        return context.getSharedPreferences(Preferences.APP_NAME, Context.MODE_PRIVATE)
    }
}