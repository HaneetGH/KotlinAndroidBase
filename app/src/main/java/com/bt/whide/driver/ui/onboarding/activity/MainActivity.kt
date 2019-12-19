package com.bt.whide.driver.ui.onboarding.activity

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.bt.whide.driver.R
import com.bt.whide.driver.data.tunnel.database.dao.UserMasterDao
import com.bt.whide.driver.helpers.AppPrefs
import com.bt.whide.driver.ui.base.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var sharedPreferences: AppPrefs

    @Inject
    lateinit var userMasterDao: UserMasterDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (sharedPreferences.isLogin) {
            Log.d("dff", userMasterDao.getAll().toString());
        }



    }

    override fun setBinding() {
    }

    override fun attachViewModel() {
    }




}
