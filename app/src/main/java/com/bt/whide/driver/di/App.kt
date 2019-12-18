package com.bt.whide.driver.di

import android.app.Activity
import android.app.Application
import com.bt.whide.driver.di.component.AppComponent
import com.bt.whide.driver.di.component.DaggerAppComponent
import com.bt.whide.driver.di.module.AppModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {
    @Inject
    lateinit var appComponent: AppComponent


    @set:Inject
    var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>? = null

    /*  *//* *//**//*  val component: AppComponent by lazy {
          DaggerAppComponent
              .builder()
              .appModule(AppModule(this))
              .build()
      }*/
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            ?.appModule(AppModule(this))
            ?.build()
        appComponent!!.inject(this)
        //AppInjector.init(this)
    }

    override fun activityInjector(): AndroidInjector<Activity?>? {
        return dispatchingAndroidInjector
    }

    
}