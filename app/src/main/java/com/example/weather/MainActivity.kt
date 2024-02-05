package com.example.weather

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.weather.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

//    db1f4a897f64d014d8c39f0f38295ed8

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    fetchWeatherData("Nagpur")

}

    private fun fetchWeatherData(cityName:String) {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .build().create(ApiInterface::class.java)

        val response =
            retrofit.getWeatherData(cityName, "db1f4a897f64d014d8c39f0f38295ed8", "metric")
        response.enqueue(object : Callback<WeatherApp> {
            override fun onResponse(call: Call<WeatherApp>, response: Response<WeatherApp>) {
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    val temperature = responseBody.main.temp.toString()

                    Log.d("tag", "$temperature")
                    binding.temperature1.text = "$temperature"
                    binding.location.text = dayName(System.currentTimeMillis()) + date(System.currentTimeMillis())

                }
            }

            override fun onFailure(call: Call<WeatherApp>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

//    to get the current day
    private fun dayName(timestamp:Long):String{
            val sdf = SimpleDateFormat("EEEE", Locale.getDefault())
            return sdf.format(Date())
        }

   private fun date(timestamp:Long):String{
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return sdf.format(Date())
    }


}