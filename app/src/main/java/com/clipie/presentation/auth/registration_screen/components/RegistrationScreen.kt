package com.clipie.presentation.auth.registration_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clipie.ui.theme.lobsterFontFamily
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun RegistrationScreen(
    navController: DestinationsNavigator
) {
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var username by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(35.dp))
        Text(
            text = "Clipie",
            fontFamily = lobsterFontFamily,
            fontSize = 60.sp,
            color = Color(38, 38, 38)
        )
        Text(
            text = "Sign up to see photos and videos\n from your friends.",
            color = Color(142, 144, 146),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it},
            label = { Text(text = "Email") },
        )
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = {Text(text = "Username")},

        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
        )
    }
}
