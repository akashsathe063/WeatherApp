package com.example.wheatherapp.repository

import android.util.Log
import com.example.wheatherapp.api.WeatherApi
import com.example.wheatherapp.db.AddUserDataBase
import com.example.wheatherapp.db.UserDetails
import com.example.wheatherapp.model.Current
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val addUserDataBase: AddUserDataBase,
    private val weatherApi: WeatherApi
) {
    private val auth = FirebaseAuth.getInstance()
    private val _loginStatus = MutableStateFlow<Boolean>(false)
    val loginStatus: StateFlow<Boolean>
        get() = _loginStatus
    val getAddUserData: Flow<List<UserDetails>>
        get() = addUserDataBase.getUserDao().getAddUserList()

    private val _weatherData = MutableStateFlow<List<Current>>(emptyList())
    val weatherData: SharedFlow<List<Current>>
        get() = _weatherData


    suspend fun userLogin(
        userName: String,
        password: String
    ) {
        if (userName.isNotEmpty() && password.isNotEmpty())
            auth.signInWithEmailAndPassword(userName, password).addOnCompleteListener {
                CoroutineScope(Dispatchers.IO).launch {
                    if (it.isSuccessful) {
                        _loginStatus.emit(true)
                    } else {
                        _loginStatus.emit(false)
                    }
                }
            }
    }

    suspend fun addUser(userDetails: UserDetails) {
        addUserDataBase.getUserDao().upsertUser(userDetails)
    }

    suspend fun getWeatherData() {
        val response = weatherApi.getWeatherData()
        if (response.isSuccessful && response.body() != null) {
            response.body()?.let {
                Log.d("getWeatherData", "getWeatherData: $it")
                _weatherData.emit(listOf(it.current))
            }
        }
    }

    suspend fun deleteUser(user:UserDetails){
        addUserDataBase.getUserDao().deleteUser(user)
    }

    suspend fun logOut(){
        auth.signOut()
    }
}