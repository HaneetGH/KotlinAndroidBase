@file:Suppress("DEPRECATION")

package com.bt.whide.driver.application

import android.app.Activity

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.multidex.MultiDexApplication

import com.bt.app.di.applyAutoInjector

import com.bt.whide.driver.di.component.AppComponent
import com.bt.whide.driver.di.component.BindingComponent
import com.bt.whide.driver.di.component.DaggerAppComponent
import com.bt.whide.driver.di.component.DaggerBindingComponent
import com.bt.whide.driver.di.module.AppModule
import com.bt.whide.driver.di.module.BindingModule
import com.squareup.picasso.Picasso
import dagger.android.*
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class App : MultiDexApplication(), HasActivityInjector, HasSupportFragmentInjector {
    @Inject
    lateinit var appComponent: AppComponent

    /* @Inject
     lateinit  var picasso: Picasso*/

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            ?.appModule(AppModule(this))
            ?.build()
        appComponent.inject(this)

        /*  val bindingComponent: BindingComponent? = DaggerBindingComponent.builder()
              ?.bindingModule(BindingModule())
              ?.picasso(picasso)
              ?.build()
          DataBindingUtil.setDefaultComponent(bindingComponent)*/

        applyAutoInjector()
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return dispatchingFragmentInjector
    }


}