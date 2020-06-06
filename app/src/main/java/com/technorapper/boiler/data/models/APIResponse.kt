package com.technorapper.boiler.data.models

import com.google.gson.annotations.SerializedName

data class APIResponse(
    @SerializedName("current")
    val current: Current? = null,
    @SerializedName("location")
    val location: LocationResponse? = null,
    @SerializedName("forecast")
    val forecast: ForecastResponse? = null
) {
    override fun toString(): String {
        return "APIResponse(current=$current, location=$location, forecast=$forecast)"
    }
}

data class Current(
    @SerializedName("temp_c")
    val tempC: String? = null,
    @SerializedName("temp_f")
    val tempF: String? = null,
    @SerializedName("humidity")
    val humidity: String? = null
) {
    override fun toString(): String {
        return "Current(tempC=$tempC, tempF=$tempF, humidity=$humidity)"
    }
}

data class LocationResponse(
    @SerializedName("region")
    val region: String? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("localtime")
    val localtime: String? = null,
    @SerializedName("lon")
    val lng: String? = null,
    @SerializedName("lat")
    val lat: String? = null,
    @SerializedName("tz_id")
    val tzId: String? = null,
    @SerializedName("name")
    val name: String? = null
) {
    override fun toString(): String {
        return "LocationResponse(region=$region, country=$country, localtime=$localtime, lng=$lng, lat=$lat, tzId=$tzId, name=$name)"
    }
}

data class ForecastResponse(
    @SerializedName("forecastday")
    val forecastdayArray: ArrayList<ForecastDay>? = null
) {
    override fun toString(): String {
        return "ForecastResponse(forecastdayArray=$forecastdayArray)"
    }
}

data class ForecastDay(
    @SerializedName("day")
    val day: Day? = null,
    @SerializedName("date")
    val date: String? = null
) {
    override fun toString(): String {
        return "ForecastDay(day=$day, date=$date)"
    }
}

data class Day(
    @SerializedName("avgtemp_c")
    val avgTempC: Double? = null,
    @SerializedName("avghumidity")
    val avgHumidity: Int? = null,
    @SerializedName("avgtemp_f")
    val avgTempF: Double? = null
) {
    override fun toString(): String {
        return "Day(avgTempC=$avgTempC, avgHumidity=$avgHumidity, avgTempF=$avgTempF)"
    }
}