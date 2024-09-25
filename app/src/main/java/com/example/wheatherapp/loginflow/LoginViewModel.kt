package com.example.wheatherapp.loginflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wheatherapp.repository.WeatherRepository
import com.google.android.play.integrity.internal.w
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {
    val logiStatus: StateFlow<Boolean>
        get() = weatherRepository.loginStatus

    fun loginStatus(username: String, password: String) {
        viewModelScope.launch {
            weatherRepository.userLogin(username, password)
        }
    }

    fun logOut(){
        viewModelScope.launch {
            weatherRepository.logOut()
        }
    }
}