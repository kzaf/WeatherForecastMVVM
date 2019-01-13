package com.kzaf.weatherforecastmvvm.data.network.response

import com.google.gson.annotations.SerializedName
import com.kzaf.weatherforecastmvvm.data.db.entity.CurrentWeatherEntry
import com.kzaf.weatherforecastmvvm.data.db.entity.WeatherLocation

data class CurrentWeatherResponse(
    val location: WeatherLocation,
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry
)