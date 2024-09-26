package com.example.wheatherapp.commonScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.wheatherapp.R
import kotlinx.coroutines.delay

//@Preview
@Composable
fun SplashScreen(modifier: Modifier = Modifier, navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(3000L)
        navController.navigate("loginScreen") {
            popUpTo("SplashScreen") { inclusive = true }
        }
    }
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_bg),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome To WeatherApp", style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight(500),
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}