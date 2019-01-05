package com.kzaf.weatherforecastmvvm.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kzaf.weatherforecastmvvm.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "fb8170162e234f0e984165343181512"

interface ApixuWeatherApiService {

    @GET("current.json")
    fun getCurrentWeather(

        @Query("q") location: String,
        @Query("lang") languageCode: String = "en"

    ) : Deferred<CurrentWeatherResponse> // Deferred is a part of kotlin co-routines and we can await

    // companion is like static methods
    companion object {
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor) : ApixuWeatherApiService {
            // requestInterceptor is like anonymous class with only one function thus we can use lambda function
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", API_KEY)
                    .build()

                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.apixu.com/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApixuWeatherApiService::class.java)
        }
    }
}