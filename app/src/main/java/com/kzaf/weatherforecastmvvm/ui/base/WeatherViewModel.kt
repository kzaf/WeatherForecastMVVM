package com.kzaf.weatherforecastmvvm.ui.base

import androidx.lifecycle.ViewModel
import com.kzaf.weatherforecastmvvm.data.provider.UnitProvider
import com.kzaf.weatherforecastmvvm.data.repository.ForecastRepository
import com.kzaf.weatherforecastmvvm.internal.UnitSystem
import com.kzaf.weatherforecastmvvm.internal.lazyDeferred

abstract class WeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : ViewModel() {

    private val unitSystem = unitProvider.getUnitSystem()

    val isMetricUnit: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weatherLocation by lazyDeferred {
        forecastRepository.getWeatherLocation()
    }
}
