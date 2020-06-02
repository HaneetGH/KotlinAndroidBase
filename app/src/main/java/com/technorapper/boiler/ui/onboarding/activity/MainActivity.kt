package com.technorapper.boiler.ui.onboarding.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.technorapper.boiler.R
import com.technorapper.boiler.data.tunnel.database.dao.UserMasterDao
import com.technorapper.boiler.data.tunnel.remote.utils.AppSocket
import com.technorapper.boiler.helpers.AppPrefs
import com.technorapper.boiler.ui.base.BaseActivity
import com.github.nkzawa.emitter.Emitter
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity() {


    private lateinit var viewModel: MainActivityViewModel
    @Inject
    lateinit var socket: AppSocket
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

       // val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {  viewModel.customSocket() }


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

      //  viewModel.getAllData();

       // viewModel.customSocket()





    }

}
