package com.bt.whide.driver.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bt.whide.driver.data.models.response.BasicResponse
import com.bt.whide.driver.data.tunnel.remote.SynchronousApi
import com.bt.whide.driver.di.scopes.ApplicationScoped
import retrofit2.await
import javax.inject.Inject


@ApplicationScoped
class MainActivityRepository @Inject internal constructor(
    private val asyncApi: SynchronousApi,
    context: Context
) : BaseRepository() {

    @SuppressLint("TimberArgCount")
    suspend fun getAllApi(): MutableLiveData<BasicResponse>? {
        val response = asyncApi.getPosts("782323523", 7)/*safeApiCall(
            call = { asyncApi.getPosts("782323523", 7) },
            errorMessage = "Error Fetching Popular Movies")*/

        if (response != null) {
            Log.d("JSON", response.result.toString())
        }
        return null
        /*withContext(Dispatchers.Main) {
            try {
                if (response != null) {


                    *//* var json = response.string().toString();
                         val moshi = Moshi.Builder().build()
                         val jsonAdapter: JsonAdapter<BasicResponse> =
                             moshi.adapter<BasicResponse>(
                                 BasicResponse::class.java
                             )

                         val blackjackHand: BasicResponse? = jsonAdapter.fromJson(json)
                         System.out.println(blackjackHand)*//*

                        //Do something with response e.g show to the UI.
                    } else {
                        Timber.d("Error: ${response}")
                    }
                } catch (e: HttpException) {
                    Timber.e("Exception ${e.message}")
                } catch (e: Throwable) {
                    Timber.e("Ooops: Something else went wrong")
                }

            }
        }*/


    }
}
