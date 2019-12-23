package com.bt.whide.driver.data.repository

import android.util.Log
import retrofit2.Response
import java.io.IOException

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result: com.bt.whide.driver.data.Result<T> = safeApiResult(call, errorMessage)
        var data: T? = null

        when (result) {
            is com.bt.whide.driver.data.Result.Success ->
                data = result.data
            is com.bt.whide.driver.data.Result.Error -> {
                Log.d("1.DataRepository", "$errorMessage & Exception - ${result.exception}")
            }
        }


        return data

    }

    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): com.bt.whide.driver.data.Result<T> {
        val response = call.invoke()
        if (response.isSuccessful) return com.bt.whide.driver.data.Result.Success(response.body()!!)

        return com.bt.whide.driver.data.Result.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }
}