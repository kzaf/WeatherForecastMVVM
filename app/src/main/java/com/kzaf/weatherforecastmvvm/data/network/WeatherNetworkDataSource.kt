package com.kzaf.weatherforecastmvvm.data.network

import androidx.lifecycle.LiveData
import com.kzaf.weatherforecastmvvm.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather( // suspend modifier indicates that the function can suspend the execution of a co-routine
        location: String,
        languageCode: String
    )

}