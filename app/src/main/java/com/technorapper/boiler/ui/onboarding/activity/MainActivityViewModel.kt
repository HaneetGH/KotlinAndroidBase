package com.technorapper.boiler.ui.onboarding.activity

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bt.whide.driver.data.models.github.repo.UserRepo
import com.technorapper.boiler.data.models.response.socket.SocketModel
import com.technorapper.boiler.data.repository.MainActivityRepository
import com.technorapper.boiler.data.tunnel.remote.utils.AppSocket
import com.technorapper.boiler.data.tunnel.remote.utils.CustomObserable
import com.technorapper.boiler.ui.base.BaseViewModel
import com.github.nkzawa.emitter.Emitter
import com.google.gson.JsonObject
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val context: Context, private val socket: AppSocket,

    private val mainActivityRepository: MainActivityRepository
) : BaseViewModel() {
    internal var responseIssue = MutableLiveData<UserRepo>()
    internal var response = MutableLiveData<Int>()
    fun getAllData() {
        CoroutineScope(Dispatchers.IO).launch { mainActivityRepository.getAllApi() }
    }

    fun getTestApi() {
        CoroutineScope(Dispatchers.IO).launch {
            var res = mainActivityRepository.getUserRepo("haneetgh")

            responseIssue.value=res;
        }
    }

    @SuppressLint("TimberArgCount")
    fun customSocket() {
        if (!socket.isConnected) {
            socket.connect()
        }
        if (socket.isConnected) {
            socket.request("news", "example FOR DRIVER SAMPLE ONLINE");
        }
        //conenct()?.subscribe(Consumer {  Log.d("Connection Type", (it as SocketModel).type.toString() + "") })
    }

    fun conenct(): Observable<Any?>? {
        return CustomObserable()
    }

    fun add(i: Int, i1: Int) {
        response.postValue(i+i1)
    }

}