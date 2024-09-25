package com.example.wheatherapp.adduser.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wheatherapp.R
import com.example.wheatherapp.adduser.AddUserViewModel
import com.example.wheatherapp.commonScreen.WeatherToolBar
import com.example.wheatherapp.weather.WeatherViewModel

@Preview
@Composable
fun AddUserScreen(modifier: Modifier = Modifier, navigateToAddUserForm: () -> Unit = {}) {
    val addUserViewModel: AddUserViewModel = hiltViewModel()
    val getAddUserList = addUserViewModel.getAddUserData.collectAsState(emptyList())
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column {
            WeatherToolBar()
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        navigateToAddUserForm()
                    },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Add User",
                    style = TextStyle(
                        fontSize = 16.sp,
//                            fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF888888)
                    )
                )

                Image(
                    painter = painterResource(id = R.drawable.baseline_add_box_24),
                    contentDescription = null
                )
            }
            LazyColumn {
                items(getAddUserList.value) {
                    AddUserListItem(
                        firstName = it.firstName,
                        lastName = it.lastName,
                        email = it.email
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun AddUserListItem(
    modifier: Modifier = Modifier,
    firstName: String = "",
    lastName: String = "",
    email: String = ""
) {
    val weatherViewModel: WeatherViewModel = hiltViewModel()
    Box(modifier = modifier
        .fillMaxWidth()
        .clickable {
            weatherViewModel.getWeatherData()
        }) {
        Column {
            Text(
                text = firstName,
                style = TextStyle(
                    fontSize = 12.sp,
//                            fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF888888)
                )
            )
            Text(
                text = lastName,
                style = TextStyle(
                    fontSize = 12.sp,
//                            fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF888888)
                )
            )
            Text(
                text = email,
                style = TextStyle(
                    fontSize = 12.sp,
//                            fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF888888)
                )
            )
        }
    }
}