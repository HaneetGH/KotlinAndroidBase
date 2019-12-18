package com.bt.whide.driver

import android.os.Bundle
import android.util.Log
import com.bt.whide.driver.data.tunnel.database.dao.UserMasterDao
import com.bt.whide.driver.helpers.AppPrefs
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var sharedPreferences: AppPrefs

    @Inject
    lateinit var userMasterDao: UserMasterDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (sharedPreferences!!.isLogin) {
            Log.d("dff", userMasterDao.getAll().toString());
        }

    }
}
