package com.bt.whide.driver.ui.onboarding.activity

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.bt.whide.driver.data.models.response.socket.SocketModel
import com.bt.whide.driver.data.repository.MainActivityRepository
import com.bt.whide.driver.data.tunnel.remote.utils.AppSocket
import com.bt.whide.driver.data.tunnel.remote.utils.CustomObserable
import com.bt.whide.driver.ui.base.BaseViewModel
import com.github.nkzawa.emitter.Emitter
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


    fun getAllData() {
        CoroutineScope(Dispatchers.IO).launch { mainActivityRepository.getAllApi() }
    }

    @SuppressLint("TimberArgCount")
    fun customSocket() {
        if (!socket.isConnected) {
            socket.connect()
        }
        if (socket.isConnected) {
            socket.request("news", "WHIDE FOR DRIVER SAMPLE ONLINE");
        }
        //conenct()?.subscribe(Consumer {  Log.d("Connection Type", (it as SocketModel).type.toString() + "") })
    }

    fun conenct(): Observable<Any?>? {
        return CustomObserable()
    }

}