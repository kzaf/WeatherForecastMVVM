package com.kzaf.weatherforecastMVVM.ui.weather.current

import androidx.lifecycle.ViewModel;
import com.kzaf.weatherforecastmvvm.data.repository.ForecastRepository
import com.kzaf.weatherforecastmvvm.internal.UnitSystem
import com.kzaf.weatherforecastmvvm.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {
    private val unitSystem = UnitSystem.METRIC // Get from settings later

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred  {
        forecastRepository.getCurrentWeather(isMetric)
    }
}
