package com.technorapper.boiler.data.tunnel.remote

import com.technorapper.boiler.data.models.response.BasicResponse
import com.google.gson.JsonObject
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query

interface SynchronousApi {
    @GET("GetMonitorNotification")
     suspend fun getPosts(
        @Query("customer_id") id: String?, @Query(
            "job_type"
        ) job_type: Int
    ): BasicResponse

}