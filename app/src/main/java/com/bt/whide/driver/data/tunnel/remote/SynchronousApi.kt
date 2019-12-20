package com.bt.whide.driver.data.tunnel.remote

import com.bt.whide.driver.data.models.response.BasicResponse
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
    ): ResponseBody

}