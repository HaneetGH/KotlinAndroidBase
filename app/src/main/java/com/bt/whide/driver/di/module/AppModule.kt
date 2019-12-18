package com.bt.whide.driver.di.module

import android.app.Application
import android.content.Context
import com.bt.whide.driver.di.scopes.ApplicationScoped
import dagger.Module
import dagger.Provides

@Module
 class AppModule(app: Application) {
    private var application: Application? = app


    @Provides
    @ApplicationScoped
    fun provideApplication(): Application? {
        return application
    }
    @Provides
    @ApplicationScoped
    fun provideContext(): Context? {
        return application
    }

}