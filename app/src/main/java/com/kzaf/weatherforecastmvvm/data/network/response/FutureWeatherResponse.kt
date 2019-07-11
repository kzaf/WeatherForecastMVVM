package com.kzaf.weatherforecastmvvm.data.network.response

import com.google.gson.annotations.SerializedName
import com.kzaf.weatherforecastmvvm.data.db.entity.WeatherLocation


data class FutureWeatherResponse(
    @SerializedName("forecastday")
    val forecast: ForecastDaysContainer,
    val location: WeatherLocation
)