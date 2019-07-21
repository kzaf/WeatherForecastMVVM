package com.kzaf.weatherforecastmvvm.data.network.response

import com.google.gson.annotations.SerializedName
import com.kzaf.weatherforecastmvvm.data.db.entity.FutureWeatherEntry


data class ForecastDaysContainer(
    @SerializedName("forecastday")
    val entries: List<FutureWeatherEntry>
)