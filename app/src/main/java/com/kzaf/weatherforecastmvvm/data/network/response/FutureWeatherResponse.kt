package com.kzaf.weatherforecastmvvm.data.network.response

import com.google.gson.annotations.SerializedName
import com.kzaf.weatherforecastmvvm.data.db.entity.WeatherLocation


data class FutureWeatherResponse(
    @SerializedName("forecast")
    val futureWeatherEntries: ForecastDaysContainer,
    val location: WeatherLocation
)