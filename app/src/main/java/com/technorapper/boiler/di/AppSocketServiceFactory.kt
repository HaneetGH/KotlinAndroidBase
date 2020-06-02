package com.technorapper.boiler.di

import com.technorapper.boiler.data.tunnel.remote.utils.AppSocket
import com.github.nkzawa.socketio.client.Manager
import java.net.URI

object AppSocketServiceFactory {

    private val baseSocketUrl: String = "http://b03f8515.ngrok.io"

    private enum class SocketNameSpace(val value: String) {
        CARD("/")
    }

    private val socketManager: Manager by lazy {
        Manager(URI(baseSocketUrl))
    }

    fun exampleSocket() = AppSocket(socketManager.socket(SocketNameSpace.CARD.value))
}