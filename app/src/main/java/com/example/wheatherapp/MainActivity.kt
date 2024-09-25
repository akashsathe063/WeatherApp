package com.example.wheatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wheatherapp.adduser.screen.AddUserForm
import com.example.wheatherapp.adduser.screen.AddUserScreen
import com.example.wheatherapp.loginflow.screen.LoginScreen
import com.example.wheatherapp.ui.theme.WheatherAppTheme
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
            AddUserScreen() {
                navController.navigate("addUserScreen")
            }
        }

        composable("addUserScreen") {
            AddUserForm() {
                navController.popBackStack()
            }
        }
    }
}