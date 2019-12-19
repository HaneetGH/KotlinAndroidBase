package com.bt.whide.driver.data.repository

import android.content.Context
import com.bt.whide.driver.data.tunnel.remote.SynchronousApi
import com.bt.whide.driver.di.scopes.ApplicationScoped
import com.google.gson.JsonObject
import io.reactivex.Observable
import javax.inject.Inject

@ApplicationScoped
class MainActivityRepository @Inject internal constructor(
    private val asyncApi: SynchronousApi,
    context: Context
) {

    fun getAllApi(): Observable<JsonObject?>? {
        return null
    }
}
