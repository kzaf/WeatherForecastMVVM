package com.kzaf.weatherforecastmvvm.data.repository

import android.os.Build
import androidx.lifecycle.LiveData
import com.kzaf.weatherforecastmvvm.data.db.CurrentWeatherDao
import com.kzaf.weatherforecastmvvm.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry
import com.kzaf.weatherforecastmvvm.data.network.WeatherNetworkDataSource
import com.kzaf.weatherforecastmvvm.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import java.time.ZonedDateTime
import java.util.*

class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : ForecastRepository {

    init {
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever { newCurrentWeather ->
            persistFetchedCurrentWeather(newCurrentWeather)
        }
    }

    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry> {
        return withContext(Dispatchers.IO){
            initWeatherData()
            return@withContext if (metric) currentWeatherDao.getWeatherMetric()
            else currentWeatherDao.getWeatherImperial()
        }
    }

    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse){
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
        }
    }

    private suspend fun initWeatherData(){
        if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
            fetchCurrentWeather()
    }

    private suspend fun fetchCurrentWeather(){
        weatherNetworkDataSource.fetchCurrentWeather(
            "Larissa",
            Locale.getDefault().language
        )
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ZonedDateTime.now().minusMinutes(30)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}