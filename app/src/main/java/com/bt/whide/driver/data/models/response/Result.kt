package com.bt.whide.driver.data.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


data class Result(
    @field:Json(name = "code")
    private val code: Int? = null,
    @field:Json(name = "msg")
    private val msg: String? = null
) {}