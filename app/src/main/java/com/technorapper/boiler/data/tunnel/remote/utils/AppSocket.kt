package com.technorapper.boiler.data.tunnel.remote.utils

import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import io.reactivex.Completable

class AppSocket(private val socket: Socket) {

    private val listeners: MutableList<CommonListener> = mutableListOf()

    val isConnected: Boolean get() = socket.connected()

    init {
        socket.on(Socket.EVENT_CONNECT, {
            listeners.forEach {
                it.onConnect(socket)
            }
        })
    }

    fun connect() {
        socket.open()
    }

    fun disconnect() {
        socket.close()
    }

    fun request(name: String, any: Any) {
        if (isConnected)
            socket.emit(name, any)

    }

    fun on(name: String, listener: Emitter.Listener) {
        socket.on(name, listener)
    }


    fun addListener(listener: CommonListener) {
        listeners.add(listener)
    }

    fun removeListener(listener: CommonListener) {
        listeners.iterator().run {
            while (hasNext())
                if (next() == listener)
                    remove()
        }
    }

    interface CommonListener {
        fun onConnect(socket: Socket)
    }
}


class SocketNotConnectedException : Throwable()