package com.example.wheatherapp.adduser.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AddUserScreen(
    modifier: Modifier = Modifier,
    navigateToWeatherScreen: () -> Unit = {},
    navigateToAddUserForm: () -> Unit = {}
) {
    val addUserViewModel: AddUserViewModel = hiltViewModel()
    val getAddUserList = addUserViewModel.getAddUserData.collectAsState(emptyList())
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
                        fontWeight = FontWeight(600),
                        color = Color.Black
                    )
                )

                Image(
                    painter = painterResource(id = R.drawable.baseline_add_box_24),
                    contentDescription = null
                )
            }
            if (getAddUserList.value.isEmpty()) {
                Box(
                    Modifier.fillMaxSize(),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.weather_bg),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.matchParentSize()
                    )
                    Column(Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "No Active User Available",
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }
                }
            } else {
                LazyColumn {
                    items(getAddUserList.value, key = {it.id}) { item ->
                        val dismissBoxState = rememberSwipeToDismissBoxState(
                            confirmValueChange = {
                                if (it == SwipeToDismissBoxValue.EndToStart) {
                                    addUserViewModel.deleteUser(item)
                                    true
                                } else {
                                    false
                                }
                            }
                        )

                        SwipeToDismissBox(state = dismissBoxState, backgroundContent = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        color = if (dismissBoxState.dismissDirection == SwipeToDismissBoxValue.EndToStart) {
                                            Color.Red
                                        } else Color.Transparent
                                    ),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.padding(20.dp)
                                )
                            }
                        }) {
                            AddUserListItem(
                                firstName = item.firstName,
                                lastName = item.lastName,
                                email = item.email,
                                navigateToWeatherScreen = navigateToWeatherScreen
                            )
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AddUserListItem(
    modifier: Modifier = Modifier,
    firstName: String = "",
    lastName: String = "",
    email: String = "",
    navigateToWeatherScreen: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                navigateToWeatherScreen()
            }
    ) {
        Text(
            text = firstName,
            modifier = Modifier.padding(4.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = lastName,
            modifier = Modifier.padding(4.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = email,
            modifier = Modifier.padding(4.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }

}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun <T> SwipeToDeleteContainer(
//    item: T,
//    onDelete: (T) -> Unit,
//    animationDuration: Int = 500,
//    content: @Composable () -> Unit
//) {
//    var isRemoved by remember {
//        mutableStateOf(false)
//    }
//    val state = rememberSwipeToDismissBoxState(
//        confirmValueChange = {
//            if (it == SwipeToDismissBoxValue.StartToEnd) {
//                isRemoved = true
//                true
//            } else {
//                false
//            }
//        }
//    )
//
//    AnimatedVisibility(visible = !isRemoved) {
//        SwipeToDismiss(state = state, background = {
//            DeleteBackGround(swipeToDismissBoxState = state)
//        },
//            dismissContent = {content(item)}
//        )
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DeleteBackGround(swipeToDismissBoxState: SwipeToDismissBoxState) {
//    val color = if (swipeToDismissBoxState.dismissDirection == SwipeToDismissBoxValue.StartToEnd) {
//        Color.Red
//    } else Color.Transparent
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(color)
//            .padding(16.dp),
//        contentAlignment = Alignment.CenterStart
//    ) {
//        Icon(
//            imageVector = Icons.Default.Delete,
//            contentDescription = null,
//            tint = Color.White
//        )
//    }
//}