package com.kzaf.weatherforecastmvvm.data.provider

import com.kzaf.weatherforecastmvvm.data.db.entity.WeatherLocation

interface LocationProvider {
    suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean
    suspend fun getPreferredLocationString(): String
}