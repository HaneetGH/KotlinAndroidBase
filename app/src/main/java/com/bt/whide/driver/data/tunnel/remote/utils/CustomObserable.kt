package com.bt.whide.driver.data.tunnel.remote.utils

import com.bt.whide.driver.data.models.response.socket.SocketModel
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.MainThreadDisposable
import java.net.URISyntaxException
import javax.inject.Inject

class CustomObserable : Observable<Any?>() {
    @Inject
    lateinit var socket: AppSocket

    override fun subscribeActual(observer: Observer<in Any?>) {
        val listener = Listener(socket, observer)
        observer.onSubscribe(listener)
        doSocketIoThing(observer)
        observer.onComplete()
        doSocketIoThing(observer)
    }

    private fun doSocketIoThing(observer: Observer<in Any?>) {

        if (socket.isConnected) {
            socket.request("news", "WHIDE CONNECTED");
        }
    }

    internal class Listener( val socket: AppSocket, bserver: Observer<in AppSocket?>?) : MainThreadDisposable() {

        override fun onDispose() {
            socket.disconnect()
        }

    }


}
