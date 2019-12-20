package com.bt.whide.driver.ui.onboarding.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bt.whide.driver.R
import com.bt.whide.driver.data.tunnel.database.dao.UserMasterDao
import com.bt.whide.driver.helpers.AppPrefs
import com.bt.whide.driver.ui.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {


    private lateinit var viewModel: MainActivityViewModel

    @Inject
    lateinit var context: Context
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
        viewModel = run {
            ViewModelProviders.of(
                this,
                viewModelFactory
            ).get(MainActivityViewModel::class.java)
        }

        viewModel.getAllData();


    }

}
