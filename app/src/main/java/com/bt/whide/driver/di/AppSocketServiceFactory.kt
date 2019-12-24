package com.bt.whide.driver.di

import com.bt.whide.driver.data.tunnel.remote.utils.AppSocket
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

    fun whideSocket() = AppSocket(socketManager.socket(SocketNameSpace.CARD.value))
}