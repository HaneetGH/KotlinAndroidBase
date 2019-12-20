package com.bt.whide.driver.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.bt.whide.driver.data.models.response.BasicResponse
import com.bt.whide.driver.data.tunnel.remote.SynchronousApi
import com.bt.whide.driver.di.scopes.ApplicationScoped
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject


@ApplicationScoped
class MainActivityRepository @Inject internal constructor(
    private val asyncApi: SynchronousApi,
    context: Context
) {

    @SuppressLint("TimberArgCount")
    fun getAllApi() {

        CoroutineScope(Dispatchers.IO).launch {
            val response = asyncApi.getPosts("782323523", 7);
            withContext(Dispatchers.Main) {
                try {
                    if (response != null) {


                       /* var json = response.string().toString();
                        val moshi = Moshi.Builder().build()
                        val jsonAdapter: JsonAdapter<BasicResponse> =
                            moshi.adapter<BasicResponse>(
                                BasicResponse::class.java
                            )

                        val blackjackHand: BasicResponse? = jsonAdapter.fromJson(json)
                        System.out.println(blackjackHand)*/

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
        }


    }
}
