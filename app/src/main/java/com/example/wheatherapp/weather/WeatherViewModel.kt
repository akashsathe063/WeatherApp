package com.example.wheatherapp.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wheatherapp.model.Current
import com.example.wheatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {
    val getWeatherCurrentData: SharedFlow<List<Current>>
        get() = weatherRepository.weatherData

    fun getWeatherData() {
        viewModelScope.launch {
            weatherRepository.getWeatherData()
        }
    }
}