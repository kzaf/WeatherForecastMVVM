package com.kzaf.weatherforecastmvvm.ui.weather.current

import com.kzaf.weatherforecastmvvm.data.provider.UnitProvider
import com.kzaf.weatherforecastmvvm.data.repository.ForecastRepository
import com.kzaf.weatherforecastmvvm.internal.lazyDeferred
import com.kzaf.weatherforecastmvvm.ui.base.WeatherViewModel

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository, unitProvider) {

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(super.isMetricUnit)
    }
}
