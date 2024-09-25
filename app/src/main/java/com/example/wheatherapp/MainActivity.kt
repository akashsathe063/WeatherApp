package com.example.wheatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wheatherapp.adduser.screen.AddUserForm
import com.example.wheatherapp.adduser.screen.AddUserScreen
import com.example.wheatherapp.loginflow.screen.LoginScreen
import com.example.wheatherapp.weather.screen.WeatherScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "loginScreen") {
        composable("loginScreen") {
            LoginScreen() {
                navController.navigate("userListScreen")
            }
        }
        composable("userListScreen") {
            AddUserScreen(navigateToWeatherScreen = {
                navController.navigate("weatherScreen")
            }) {
                navController.navigate("addUserScreen")
            }
        }

        composable("addUserScreen") {
            AddUserForm() {
                navController.popBackStack()
            }
        }
        composable("weatherScreen") {
            WeatherScreen(onLogOutPress = {
                navController.popBackStack(navController.graph.startDestinationId, true)
                navController.navigate(navController.graph.startDestinationId)
            }) {
                navController.popBackStack()
            }
        }
    }
}