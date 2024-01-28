package com.clipie.presentation.login_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clipie.R
import com.clipie.ui.theme.lobsterFontFamily
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
@Destination
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: DestinationsNavigator
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFca02d2)),
    ) {
        Spacer(modifier = Modifier.height(220.dp))
        Text(
            text = "Clipie",
            fontFamily = lobsterFontFamily,
            fontSize = 60.sp,
            color = Color(0xFFede6f4)
        )
        LoginScreenTextField(
            value = "",
            onValueChange = {},
            label = "Email"
        )
        LoginScreenPasswordTextField(
            value = "",
            onValueChange = {},
            label = "Password",
            modifier = modifier.padding(top = 5.dp),
        )
        Row {
            Spacer(modifier = Modifier.width(140.dp))
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(R.string.forgot_password),
                    color = Color.White
                )
            }
        }
        LoginScreenButton(modifier = Modifier.width(275.dp), onClick = {}, text = "Login")
    }
}

@Composable
fun LoginScreenTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                color = Color(0xFFFAFAFA)
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFF78017C),
            focusedContainerColor = Color(0xFF78017C),
            errorContainerColor = Color(0xFF78017C),
            unfocusedIndicatorColor = Color(0xFFca02d2),
            focusedIndicatorColor = Color(0xFFca02d2),
            errorIndicatorColor = Color(0xFFca02d2),
            cursorColor = Color(0xFFFAFAFA),
            errorCursorColor = Color(0xFFFAFAFA)
        ),
        shape = RoundedCornerShape(50),
        modifier = modifier
    )
}

@Composable
fun LoginScreenPasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
) {
    var showPassword by rememberSaveable {
        mutableStateOf(false)
    }
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                color = Color(0xFFFAFAFA)
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFF78017C),
            focusedContainerColor = Color(0xFF78017C),
            errorContainerColor = Color(0xFF78017C),
            unfocusedIndicatorColor = Color(0xFFca02d2),
            focusedIndicatorColor = Color(0xFFca02d2),
            errorIndicatorColor = Color(0xFFca02d2),
            cursorColor = Color(0xFFFAFAFA),
            errorCursorColor = Color(0xFFFAFAFA),
            focusedLeadingIconColor = Color(0xFFFAFAFA),
            unfocusedTrailingIconColor = Color(0xFFFAFAFA)
        ),
        trailingIcon = {
                if (showPassword) {
                    IconButton(onClick = { showPassword = false }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_visibility_on),
                            contentDescription = "Password visible")
                    }
                } else {
                    IconButton(onClick = { showPassword = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_visibility_off),
                            contentDescription = "Password invisible"
                        )
                    }
                }
        },
        shape = RoundedCornerShape(50),
        modifier = modifier
    )
}

@Composable
fun LoginScreenButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        border = BorderStroke(2.dp, Color(0xFF78017C))
     ) {
        Text(
            text = text,
            color = Color(0xFFFAFAFA)
        )
    }
}