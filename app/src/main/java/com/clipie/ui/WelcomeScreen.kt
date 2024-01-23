package com.clipie.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clipie.R
import com.ramcosta.composedestinations.annotation.Destination

@Destination(start = true)
@Composable
fun WelcomeScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFF6FF2DD), Color(0xBBC900DF)),
                )
            )
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Welcome to", color = Color.White, fontWeight = FontWeight.W400, fontSize = 50.sp
        )
        Text(text = "Clipie", color = Color.White, fontWeight = FontWeight.W700, fontSize = 50.sp)
        Spacer(modifier = Modifier.height(130.dp))
        Image(painter = painterResource(id = R.drawable.app_logo), contentDescription = null, modifier = Modifier.size(140.dp))
        Spacer(modifier = Modifier.height(180.dp))
        OutlinedButton(
            border = BorderStroke(1.dp, Color.White),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
            onClick = { },
            contentPadding = PaddingValues(horizontal = 80.dp, vertical = 15.dp)
        ) {
            Text(
                text = "Log in".uppercase(), fontSize = 20.sp, fontWeight = FontWeight.W800
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row {
            Text(
                text = "Don't have account?",
                color = Color.White,
                modifier = Modifier.padding(top = 14.dp)
            )
            TextButton(
                onClick = { /*TODO*/ },
            ) {
                Text(
                    text = "Sign up",
                    textDecoration = TextDecoration.Underline,
                    color = Color.White,

                )
            }
        }
    }
}

