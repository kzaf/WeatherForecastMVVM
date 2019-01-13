package com.kzaf.weatherforecastmvvm

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.kzaf.weatherforecastmvvm.data.db.ForecastDatabase
import com.kzaf.weatherforecastmvvm.data.network.*
import com.kzaf.weatherforecastmvvm.data.provider.LocationProvider
import com.kzaf.weatherforecastmvvm.data.provider.LocationProviderImpl
import com.kzaf.weatherforecastmvvm.data.provider.UnitProvider
import com.kzaf.weatherforecastmvvm.data.provider.UnitProviderImpl
import com.kzaf.weatherforecastmvvm.data.repository.ForecastRepository
import com.kzaf.weatherforecastmvvm.data.repository.ForecastRepositoryImpl
import com.kzaf.weatherforecastmvvm.ui.weather.current.CurrentWeatherViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ForecastApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))

        bind() from singleton { ForecastDatabase(instance()) }
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDao() }
        bind() from singleton { instance<ForecastDatabase>().weatherLocationDao() }
        bind<ConnectivityInterceptor>() with singleton{ ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApixuWeatherApiService(instance()) }
        bind<WeatherNetworkDataSource>() with singleton{ WeatherNetworkDataSourceImpl(instance()) }
        bind<LocationProvider>() with singleton { LocationProviderImpl() }
        bind<ForecastRepository>() with singleton{ ForecastRepositoryImpl(instance(), instance(), instance(), instance()) }
        bind<UnitProvider>() with singleton{ UnitProviderImpl(instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance(), instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this) // ThreeTen project provides a date and time API for Java
    }
}