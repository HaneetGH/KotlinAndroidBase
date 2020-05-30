@file:Suppress("DEPRECATION")

package com.technorapper.boiler.application

import android.app.Activity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.multidex.MultiDexApplication
import com.technorapper.boiler.di.applyAutoInjector
import com.technorapper.boiler.data.tunnel.remote.utils.AppSocket
import com.technorapper.boiler.di.component.AppComponent
import com.technorapper.boiler.di.component.BindingComponent
import com.technorapper.boiler.di.component.DaggerAppComponent
import com.technorapper.boiler.di.component.DaggerBindingComponent
import com.technorapper.boiler.di.module.AppModule
import com.technorapper.boiler.di.module.BindingModule
import com.github.nkzawa.socketio.client.Socket
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class App : MultiDexApplication(), HasActivityInjector, HasSupportFragmentInjector {
    @Inject
    lateinit var appComponent: AppComponent

    @Inject
    lateinit var picasso: Picasso

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingFragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var socket: AppSocket

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .application(this)
            ?.appModule(AppModule(this))
            ?.build()
        appComponent.inject(this)
        socket.connect()
        val bindingComponent: BindingComponent? = DaggerBindingComponent.builder()
            ?.bindingModule(BindingModule())
            ?.picasso(picasso)
            ?.build()
        DataBindingUtil.setDefaultComponent(bindingComponent)
        applyAutoInjector()
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return dispatchingFragmentInjector
    }


}