package com.example.wheatherapp.adduser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wheatherapp.db.UserDetails
import com.example.wheatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {
    val getAddUserData: Flow<List<UserDetails>>
        get() = weatherRepository.getAddUserData

    fun addUserInDb(userDetails: UserDetails) {
        viewModelScope.launch {
            if (userDetails.firstName.isNotEmpty() && userDetails.lastName.isNotEmpty() && userDetails.email.isNotEmpty())
                weatherRepository.addUser(userDetails)
        }
    }

    fun deleteUser(userDetails: UserDetails){
        viewModelScope.launch {
            weatherRepository.deleteUser(userDetails)
        }
    }
}