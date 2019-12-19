package com.bt.whide.driver.data.tunnel.remote

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface SynchronousApi {
    @GET
    suspend fun getPosts(): Response<JsonObject>

}