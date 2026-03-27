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
import kotlin.getValue

@Suppress("TYPE_INTERSECTION_AS_REIFIED_WARNING",
    "INFERRED_TYPE_VARIABLE_INTO_EMPTY_INTERSECTION_WARNING", "DEPRECATION"
)
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val weatherViewModel: WeatherViewModel by viewModels()
    private val calendar by lazy { Calendar.getInstance() }
    private val forecastAdapter by lazy { ForecastAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.WeatherView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor = Color.TRANSPARENT
        }


        binding.apply {
            var lat=intent.getDoubleExtra("lat",0.00)
            var lon=intent.getDoubleExtra("lon",0.00)
            var name=intent.getStringExtra("name")
            if(lat==0.0) {
                lat = 51.50
                lon = -0.12
                name = "London"
            }
            binding.imgFarmer.setOnClickListener {
                val intent = Intent(this@MainActivity, FarmerActivity::class.java)
                startActivity(intent)
            }
            addCity.setOnClickListener {
                startActivity(Intent(this@MainActivity, CityListActivity::class.java))
            }
            cityText.text = name
            progressBar.visibility = View.VISIBLE
            weatherViewModel.loadCurrentWeather(lat, lon, "metric").enqueue(object :
                Callback<CurrentResponseApi> {
                override fun onResponse(
                    call: Call<CurrentResponseApi>,
                    response: Response<CurrentResponseApi>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()
                        progressBar.visibility = View.GONE
                        detailLayout.visibility = View.VISIBLE
                        data?.let {
                            humidityTxt.text = it.main?.humidity?.toString() + "%"
                            statusText.text = it.weather?.get(0)?.main ?: "-"
                            windTxt.text = it.wind?.speed?.let { Math.round(it).toString() } + "Km"
                            currentTempText.text =
                                it.main?.temp?.let { Math.round(it).toString() } + "°"
                            maxTempTxt.text =
                                it.main?.tempMax?.let { Math.round(it).toString() } + "°"
                            minTempTxt.text =
                                it.main?.tempMin?.let { Math.round(it).toString() } + "°"
                            val drawable = if (isNightNow()) R.drawable.night
                            else {
                                setDynamicallyWallpapaer(it.weather?.get(0)?.icon ?: "-")
                            }
                            bgimage.setImageResource(drawable)

                        }

                    }
                }

                override fun onFailure(call: Call<CurrentResponseApi?>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.toString(), Toast.LENGTH_SHORT).show()
                }
            })
            weatherViewModel.loadForecastWeather(lat, lon, "metric")
                .enqueue(object : Callback<ForecastResponseApi> {
                    override fun onResponse(
                        call: Call<ForecastResponseApi?>,
                        response: Response<ForecastResponseApi?>
                    ) {
                        if (response.isSuccessful) {
                            val data = response.body()
                            blueView.visibility = View.VISIBLE
                            data?.let {
                                forecastAdapter.differ.submitList(it.list)
                                forecastView.apply {
                                    layoutManager = LinearLayoutManager(
                                        this@MainActivity,
                                        LinearLayoutManager.HORIZONTAL, false
                                    )
                                    adapter = forecastAdapter

                                }
                            }

                        }
                    }

                    override fun onFailure(
                        call: Call<ForecastResponseApi?>,
                        t: Throwable
                    ) {

                    }

                })
        }

    }

    private fun isNightNow(): Boolean {
        return calendar.get(Calendar.HOUR_OF_DAY) >= 18

    }

    private fun setDynamicallyWallpapaer(icon: String): Int {
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
