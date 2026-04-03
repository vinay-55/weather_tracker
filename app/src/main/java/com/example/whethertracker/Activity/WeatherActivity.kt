package com.example.whethertracker.Activity

import android.content.Intent
import android.graphics.Color
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.whethertracker.Adapter.ForecastAdapter
import com.example.whethertracker.Model.CurrentResponseApi
import com.example.whethertracker.Model.ForecastResponseApi
import com.example.whethertracker.R
import com.example.whethertracker.ViewModel.WeatherViewModel
import com.example.whethertracker.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val weatherViewModel: WeatherViewModel by viewModels()
    private val calendar by lazy { Calendar.getInstance() }
    private val forecastAdapter by lazy { ForecastAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.TRANSPARENT
        }
        binding.btnFarmer.setOnClickListener {
            startActivity(Intent(this, FarmerMenuActivity::class.java))
        }
       binding.addCity.setOnClickListener {
                startActivity(Intent(this, CityListActivity::class.java))

        }

        loadWeather()
    }

    private fun loadWeather() {

        var lat = intent.getDoubleExtra("lat", 0.0)
        var lon = intent.getDoubleExtra("lon", 0.0)
        var name = intent.getStringExtra("name")

        if (lat == 0.0) {
            lat = 51.50
            lon = -0.12
            name = "London"
        }

        binding.cityText.text = name
        binding.progressBar.visibility = View.VISIBLE

        weatherViewModel.loadCurrentWeather(lat, lon, "metric")
            .enqueue(object : Callback<CurrentResponseApi> {

                override fun onResponse(
                    call: Call<CurrentResponseApi>,
                    response: Response<CurrentResponseApi>
                ) {
                    if (response.isSuccessful) {

                        val data = response.body()
                        binding.progressBar.visibility = View.GONE
                        binding.detailLayout.visibility = View.VISIBLE

                        data?.let {

                            binding.humidityTxt.text = "${it.main?.humidity}%"
                            binding.statusText.text = it.weather?.get(0)?.main ?: "-"
                            binding.windTxt.text = "${it.wind?.speed?.toInt()} Km"
                            binding.currentTempText.text = "${it.main?.temp?.toInt()}°"
                            binding.maxTempTxt.text = "${it.main?.tempMax?.toInt()}°"
                            binding.minTempTxt.text = "${it.main?.tempMin?.toInt()}°"

                            val drawable = if (isNightNow()) {
                                R.drawable.night
                            } else {
                                setWallpaper(it.weather?.get(0)?.icon ?: "")
                            }

                            binding.bgimage.setImageResource(drawable)
                        }
                    }
                }

                override fun onFailure(call: Call<CurrentResponseApi>, t: Throwable) {
                    Toast.makeText(this@WeatherActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            })

        weatherViewModel.loadForecastWeather(lat, lon, "metric")
            .enqueue(object : Callback<ForecastResponseApi> {

                override fun onResponse(
                    call: Call<ForecastResponseApi>,
                    response: Response<ForecastResponseApi>
                ) {
                    if (response.isSuccessful) {

                        val data = response.body()
                        binding.blueView.visibility = View.VISIBLE

                        data?.let {
                            forecastAdapter.differ.submitList(it.list)

                            binding.forecastView.apply {
                                layoutManager = LinearLayoutManager(
                                    this@WeatherActivity,
                                    LinearLayoutManager.HORIZONTAL,
                                    false
                                )
                                adapter = forecastAdapter
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ForecastResponseApi>, t: Throwable) {}
            })
    }

    private fun isNightNow(): Boolean {
        return calendar.get(Calendar.HOUR_OF_DAY) >= 18
    }

    private fun setWallpaper(icon: String): Int {
        return when (icon.dropLast(1)) {
            "01" -> R.drawable.sunny
            "02", "03", "04" -> R.drawable.cloudy
            "09", "10", "11" -> R.drawable.rainy
            "13" -> R.drawable.snow
            "50" -> R.drawable.haze
            else -> R.drawable.sunny
        }
    }
}