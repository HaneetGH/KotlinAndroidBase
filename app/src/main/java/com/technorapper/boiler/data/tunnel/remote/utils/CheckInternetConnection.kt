package com.technorapper.boiler.data.tunnel.remote.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import java.net.InetAddress

class CheckInternetConnection(private val _context: Context) {

    fun isNetworkAvailable(): Boolean { /*ConnectivityManager connectivityManager
                = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();*/
        return isOnline()
    }

    companion object {
        fun isInternetConnected(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }

    fun isOnline(): Boolean {
        try {
            val p1 =
                Runtime.getRuntime().exec("ping -c 1 www.google.com")
            val returnVal = p1.waitFor()
            return returnVal == 0
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

}
