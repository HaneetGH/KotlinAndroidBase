package com.bt.whide.driver

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bt.whide.driver.helpers.AppPrefs
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var sharedPreferences: AppPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (sharedPreferences!!.isLogin) {

        }

    }
}
