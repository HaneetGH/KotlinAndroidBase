package com.technorapper.boiler.data.repository


import android.util.Log
import com.squareup.picasso.Picasso
import retrofit2.Response
import java.io.IOException

open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result: com.technorapper.boiler.data.Result<T> = safeApiResult(call, errorMessage)
        var data: T? = null

        when (result) {
            is com.technorapper.boiler.data.Result.Success ->
                data = result.data
            is com.technorapper.boiler.data.Result.Error -> {
                Log.d("1.DataRepository", "$errorMessage & Exception - ${result.exception}")
            }
        }


        return data

    }

    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>,
        errorMessage: String
    ): com.technorapper.boiler.data.Result<T> {
        val response = call.invoke()
        if (response.isSuccessful) return com.technorapper.boiler.data.Result.Success(response.body()!!)

        return com.technorapper.boiler.data.Result.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }
}