package com.bt.whide.driver.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bt.whide.driver.di.Injectable
import com.bt.whide.driver.di.WDViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity(), Injectable {
   /* @Inject
    lateinit var viewModeFactory: WDViewModelFactory*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        attachViewModel()

    }

    protected abstract fun setBinding()

    protected abstract fun attachViewModel()
}