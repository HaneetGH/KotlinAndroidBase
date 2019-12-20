package com.bt.whide.driver.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bt.whide.driver.di.Injectable
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity(), Injectable, HasSupportFragmentInjector{

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        attachViewModel()

    }

    protected abstract fun setBinding()

    protected abstract fun attachViewModel()

    override fun supportFragmentInjector() = fragmentDispatchingAndroidInjector

}