package com.kzaf.weatherforecastmvvm.data.network.response

import com.kzaf.weatherforecastmvvm.data.db.entity.FutureWeatherEntry


data class ForecastDaysContainer(
    val entries: List<FutureWeatherEntry>
)