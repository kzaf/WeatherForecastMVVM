package com.kzaf.weatherforecastmvvm.data.provider

import com.kzaf.weatherforecastmvvm.data.db.entity.WeatherLocation

class LocationProviderImpl : LocationProvider {
    override suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean {
        return true
    }

    override suspend fun getPreferredLocationString(): String {
        return "Hamburg"
    }
}