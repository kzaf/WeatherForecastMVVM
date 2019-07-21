package com.kzaf.weatherforecastmvvm.ui.weather.future.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kzaf.weatherforecastMVVM.ui.weather.future.detail.FutureDetailWeatherViewModel
import com.kzaf.weatherforecastmvvm.data.provider.UnitProvider
import com.kzaf.weatherforecastmvvm.data.repository.ForecastRepository
import org.threeten.bp.LocalDate


class FutureDetailWeatherViewModelFactory(
    private val detailDate: LocalDate,
    private val forecastRepository: ForecastRepository,
    private val unitProvider: UnitProvider
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FutureDetailWeatherViewModel(detailDate, forecastRepository, unitProvider) as T
    }
}
