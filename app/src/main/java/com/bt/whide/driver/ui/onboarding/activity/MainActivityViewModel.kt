package com.bt.whide.driver.ui.onboarding.activity

import android.content.Context
import android.content.SharedPreferences
import com.bt.whide.driver.data.repository.MainActivityRepository
import com.bt.whide.driver.ui.base.BaseViewModel
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val context: Context,
    private val mainActivityRepository: MainActivityRepository
) : BaseViewModel() {


    fun getAllData() {
        /*       addToDisposable(mainActivityRepository.getAllApi().subscribe(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()))
        */
    }

}