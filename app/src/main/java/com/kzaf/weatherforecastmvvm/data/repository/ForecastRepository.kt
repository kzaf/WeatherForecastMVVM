package com.kzaf.weatherforecastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.kzaf.weatherforecastmvvm.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
}