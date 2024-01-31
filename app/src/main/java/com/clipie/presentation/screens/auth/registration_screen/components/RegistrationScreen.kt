package com.clipie.presentation.screens.auth.registration_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clipie.R
import com.clipie.presentation.screens.auth.components.AuthenticationButton
import com.clipie.presentation.screens.auth.components.AuthenticationPasswordTextField
import com.clipie.presentation.screens.auth.components.AuthenticationTextField
import com.clipie.ui.theme.Background
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator
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
        modifier = modifier
            .fillMaxSize()
            .background(color = Background)
    ) {
        Text(
            text = stringResource(R.string.create_new_account),
            modifier = Modifier.padding(end = 100.dp, top = 20.dp, start = 30.dp),
            fontSize = 40.sp,
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(170.dp))
        AuthenticationTextField(
            value = "",
            onValueChange = {},
            label = stringResource(R.string.email),
            imeAction = ImeAction.Next
        )
        AuthenticationTextField(
            value = "",
            onValueChange = {},
            label = stringResource(R.string.username),
            imeAction = ImeAction.Done,
            modifier = modifier.padding(top = 5.dp),
        )
        AuthenticationPasswordTextField(
            value = "",
            onValueChange = {},
            label = stringResource(R.string.password),
            imeAction = ImeAction.Done,
            modifier = modifier.padding(top = 5.dp),
        )
        AuthenticationButton(
            modifier = Modifier
                .width(275.dp)
                .padding(10.dp)
            , onClick = {},
            text = stringResource(id = R.string.create_account)
        )
        TextButton(
            onClick = { navigator.navigateUp() },
            modifier = Modifier.padding(top = 200.dp)
        ) {
            Text(
                text = stringResource(R.string.already_have_an_account),
                color = Color(0xFF0064e0),
            )
        }
    }
}
