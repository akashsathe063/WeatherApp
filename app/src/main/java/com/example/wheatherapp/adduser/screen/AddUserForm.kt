package com.example.wheatherapp.adduser.screen

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wheatherapp.R
import com.example.wheatherapp.adduser.AddUserViewModel
import com.example.wheatherapp.commonScreen.WeatherToolBar
import com.example.wheatherapp.db.UserDetails

@Preview
@Composable
fun AddUserForm(modifier: Modifier = Modifier, navigateToUserListScreen: () -> Unit = {}) {
    val addUserViewModel: AddUserViewModel = hiltViewModel()
    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var isEmailValid by remember { mutableStateOf(true) }
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.weather_bg),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            WeatherToolBar()
            Column(
                modifier = Modifier
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TextField(
                    value = firstName,
                    onValueChange = { firstName = it },
                    label = { Text("First Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = lastName,
                    onValueChange = { lastName = it },
                    label = { Text("Last Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = email,
                    onValueChange = {
                        email = it
                        isEmailValid = EmailValidation(email = it)
                    },
                    isError = !isEmailValid,
                    label = { Text("Email") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                if (!isEmailValid) {
                    Text(text = "Invalid email address",color = Color.Red)
                }

                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = {
                        navigateToUserListScreen()
                    }) {
                        Text(text = "Cancel")
                    }

                    Button(onClick = {
                        if (!isEmailValid) {
                            return@Button
                        }
                        addUserViewModel.addUserInDb(
                            UserDetails(
                                firstName = firstName.trim(),
                                lastName = lastName.trim(),
                                email = email.trim()
                            )
                        )
                        if (firstName.trim().isNotEmpty() &&
                            lastName.trim().isNotEmpty() &&
                            email.trim().isNotEmpty()
                        ) {
                            navigateToUserListScreen()
                        }
                    }) {
                        Text(text = "Save")
                    }
                }
            }
        }
    }
}


fun EmailValidation(email: String): Boolean {
    val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$")
    return emailRegex.matches(email)
}