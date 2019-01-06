package com.kzaf.weatherforecastmvvm.ui.weather.current

import androidx.lifecycle.ViewModel;
import com.kzaf.weatherforecastmvvm.data.provider.UnitProvider
import com.kzaf.weatherforecastmvvm.data.repository.ForecastRepository
import com.kzaf.weatherforecastmvvm.internal.UnitSystem
import com.kzaf.weatherforecastmvvm.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : ViewModel() {
    private val unitSystem = unitProvider.getUnitSystem()

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred  {
        forecastRepository.getCurrentWeather(isMetric)
    }
}
