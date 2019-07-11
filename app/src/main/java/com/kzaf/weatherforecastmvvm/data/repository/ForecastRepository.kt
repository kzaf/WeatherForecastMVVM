package com.kzaf.weatherforecastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.kzaf.weatherforecastmvvm.data.db.entity.WeatherLocation
import com.kzaf.weatherforecastmvvm.data.db.unitlocalized.current.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
    suspend fun getWeatherLocation(): LiveData<WeatherLocation>
}