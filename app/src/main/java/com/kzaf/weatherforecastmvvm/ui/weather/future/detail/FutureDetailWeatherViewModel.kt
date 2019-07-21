package com.kzaf.weatherforecastMVVM.ui.weather.future.detail

import com.kzaf.weatherforecastmvvm.data.provider.UnitProvider
import com.kzaf.weatherforecastmvvm.data.repository.ForecastRepository
import com.kzaf.weatherforecastmvvm.internal.lazyDeferred
import com.kzaf.weatherforecastmvvm.ui.base.WeatherViewModel
import org.threeten.bp.LocalDate

class FutureDetailWeatherViewModel(
    private val detailDate: LocalDate,
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : WeatherViewModel(forecastRepository, unitProvider) {

    val weather by lazyDeferred {
        forecastRepository.getFutureWeatherByDate(detailDate, super.isMetricUnit)
    }
}
