package com.kzaf.weatherforecastmvvm.data.network

import androidx.lifecycle.LiveData
import com.kzaf.weatherforecastmvvm.data.network.response.CurrentWeatherResponse
import com.kzaf.weatherforecastmvvm.data.network.response.FutureWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
    val downloadedFutureWeather: LiveData<FutureWeatherResponse>

    suspend fun fetchCurrentWeather( // suspend modifier indicates that the function can suspend the execution of a co-routine
        location: String,
        languageCode: String
    )

    suspend fun fetchFutureWeather( // suspend modifier indicates that the function can suspend the execution of a co-routine
        location: String,
        languageCode: String
    )

}