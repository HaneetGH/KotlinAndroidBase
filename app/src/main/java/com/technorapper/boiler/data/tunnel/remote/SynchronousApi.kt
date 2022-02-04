package com.technorapper.boiler.data.tunnel.remote

import com.bt.technorapper.driver.data.models.github.repo.UserRepo
import com.technorapper.boiler.data.models.response.BasicResponse
import com.google.gson.JsonObject
import com.technorapper.boiler.data.models.APIResponse
import io.reactivex.Flowable
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface SynchronousApi {
    @GET("GetMonitorNotification")
    suspend fun getPosts(
        @Query("customer_id") id: String?, @Query(
            "job_type"
        ) job_type: Int
    ): BasicResponse

    @GET
    fun getForecastTempWithUrl(
        @Url
        url: String
    ): Flowable<APIResponse>

    @GET("users/{name}/repos")
    suspend fun getUserRepo(
        @Path(
            "name"
        ) name: String?
    ): UserRepo
}