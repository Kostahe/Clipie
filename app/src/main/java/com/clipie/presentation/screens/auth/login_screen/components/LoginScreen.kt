package com.clipie.presentation.screens.auth.login_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clipie.R
import com.clipie.presentation.screens.auth.components.AuthenticationButton
import com.clipie.presentation.screens.auth.components.AuthenticationOutlinedButton
import com.clipie.presentation.screens.auth.components.AuthenticationPasswordTextField
import com.clipie.presentation.screens.auth.components.AuthenticationTextField
import com.clipie.ui.theme.Background
import com.clipie.ui.theme.lobsterFontFamily
import com.ramcosta.composedestinations.annotation.Destination


@Composable
@Preview
@Destination
fun LoginScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(color = Background)
    ) {
        Spacer(modifier = Modifier.height(170.dp))
        Icon(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.padding(bottom = 30.dp)
        )
        AuthenticationTextField(
            value = "",
            onValueChange = {},
            label = stringResource(R.string.email),
            imeAction = ImeAction.Next
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
            , onClick = {}, text = stringResource(R.string.log_in)
        )
        TextButton(onClick = { }) {
            Text(
                text = stringResource(R.string.forgot_password), color = Color(0xFF1c2b33)
            )
        }
        AuthenticationOutlinedButton(
            modifier = Modifier
                .width(275.dp)
                .padding(top = 200.dp),
            onClick = { /*TODO*/ },
            text = stringResource(R.string.create_new_account)
        )
    }
}