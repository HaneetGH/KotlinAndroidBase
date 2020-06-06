package com.technorapper.boiler.ui.onboarding.activity

import android.util.Log
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.technorapper.boiler.application.App
import com.technorapper.boiler.di.module.AppModule
import io.mockk.every
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import timber.log.Timber
import javax.inject.Inject

class MainActivityDaggerTest {


    @Inject
    lateinit var appAPIs: Picasso

    @Inject
    lateinit var viewModel: MainActivityViewModel

    @Before
    fun setUp() {
        val component = DaggerTestAppComponent.builder()
            .application(App())
            .appModule(TestApplicationModule(App()))
            .build()
        component.into(this)
    }

    @Test
    fun `my test`() {
        assertNotNull(appAPIs)
        assertNotNull(viewModel)

        //viewModel.getTestApi()
        viewModel.add(1, 2)
        GlobalScope.launch {
            viewModel.response.observeForever { t: Int? ->
                Log.d(
                    "check",
                    t.toString()
                )
            }
        }
        GlobalScope.launch {
            viewModel.responseIssue.observeForever { }

               assert(viewModel.response.value == 3)


        }
    }
}