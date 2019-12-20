package com.bt.whide.driver.data.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class BasicResponse(

    @field:Json(name = "result")
    val result: Result? = null
) {}