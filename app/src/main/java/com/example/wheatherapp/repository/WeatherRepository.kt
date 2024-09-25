package com.example.wheatherapp.repository

import android.util.Log
import com.example.wheatherapp.db.AddUserDataBase
import com.example.wheatherapp.db.UserDetails
import com.google.firebase.auth.AdditionalUserInfo
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val addUserDataBase: AddUserDataBase) {
    private val auth = FirebaseAuth.getInstance()
    private val _loginStatus = MutableStateFlow<Boolean>(false)
    val loginStatus: StateFlow<Boolean>
        get() = _loginStatus
    val getAddUserData: Flow<List<UserDetails>>
        get() = addUserDataBase.getUserDao().getAddUserList()

    suspend fun userLogin(
        userName: String,
        password: String
    ) {
        if (userName.isNotEmpty() && password.isNotEmpty())
            auth.signInWithEmailAndPassword(userName, password).addOnCompleteListener {
                CoroutineScope(Dispatchers.IO).launch {
                    if (it.isSuccessful) {
                        Log.d("WeatherRepository", "userLogin: succefule")
                        _loginStatus.emit(true)
                    } else {
                        _loginStatus.emit(false)
                        Log.d("WeatherRepository", "userLogin: failure")
                    }
                }
            }
    }

    suspend fun addUser(userDetails: UserDetails) {
        addUserDataBase.getUserDao().upsertUser(userDetails)
    }

}