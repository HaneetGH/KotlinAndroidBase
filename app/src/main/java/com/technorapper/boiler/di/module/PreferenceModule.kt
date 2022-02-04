package com.technorapper.boiler.di.module

import android.content.Context
import android.content.SharedPreferences
import com.technorapper.boiler.constants.Preferences

import com.technorapper.boiler.application.App
import com.technorapper.boiler.di.scopes.ApplicationScoped

import com.technorapper.boiler.helpers.AppPrefs
import dagger.Module
import dagger.Provides

@Module
open class PreferenceModule {

    @Provides
    @ApplicationScoped
   open fun provideTechnorapperPreference(preferences: SharedPreferences?): AppPrefs {
        return AppPrefs(preferences)
    }
    
    @Provides
    @ApplicationScoped
    open fun providePreferences(context: App): SharedPreferences {
        return context.getSharedPreferences(Preferences.APP_NAME, Context.MODE_PRIVATE)
    }
}