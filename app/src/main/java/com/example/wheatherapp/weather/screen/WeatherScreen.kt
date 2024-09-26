package com.example.wheatherapp.weather.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wheatherapp.R
import com.example.wheatherapp.commonScreen.WeatherToolBar
import com.example.wheatherapp.loginflow.LoginViewModel
import com.example.wheatherapp.weather.WeatherViewModel

@Preview
@Composable
fun WeatherScreen(
    modifier: Modifier = Modifier,
    onLogOutPress: () -> Unit = {},
    onBackPress: () -> Unit = {}
) {
    val weatherViewModel: WeatherViewModel = hiltViewModel()
    val loginViewModel: LoginViewModel = hiltViewModel()
    LaunchedEffect(key1 = Unit) {
        weatherViewModel.getWeatherData()
    }
    val weatherData = weatherViewModel.getWeatherCurrentData.collectAsState(initial = emptyList())
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.weather_bg),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column {
            WeatherToolBar()
            Spacer(modifier = Modifier.height(16.dp))
            if (weatherData.value.isEmpty()) {
                Row(
                    Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Empty Weather Detail",
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            } else {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Today's Weather",
                        style = MaterialTheme.typography.headlineLarge
                    )
                }

                WeatherDataItem(
                    weatherDataType = "Temp",
                    weatherDataValue = weatherData.value[0].temp.toString()
                )
                WeatherDataItem(
                    weatherDataType = "Weather Type",
                    weatherDataValue = weatherData.value[0].weather[0].main
                )
                WeatherDataItem(
                    weatherDataType = "Humidity",
                    weatherDataValue = weatherData.value[0].humidity.toString()
                )
                WeatherDataItem(
                    weatherDataType = "Wind Speed",
                    weatherDataValue = weatherData.value[0].wind_speed.toString()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = {
                        onBackPress()
                    }) {
                        Text(text = "Back")
                    }

                    Button(onClick = {
                        loginViewModel.logOut()
                        onLogOutPress()
                    }) {
                        Text(text = "LogOut")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun WeatherDataItem(
    modifier: Modifier = Modifier,
    weatherDataType: String = "Temperature",
    weatherDataValue: String = "30"
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = weatherDataType,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(8.dp),
                    fontWeight = FontWeight(500),
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = weatherDataValue,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight(400),
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}