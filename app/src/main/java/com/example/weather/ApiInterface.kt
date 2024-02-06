package com.example.weather

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.weather.WeatherApp as WeatherApp1


interface ApiInterface {

    @GET("weather")
    fun getWeatherData(
        @Query("q") city : String ,
        @Query("appid") appid : String ,
        @Query("units") units:String
    ) : retrofit2.Call<WeatherApp1>

}