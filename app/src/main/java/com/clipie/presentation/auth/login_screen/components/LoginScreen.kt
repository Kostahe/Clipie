package com.clipie.presentation.auth.login_screen.components

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.clipie.R
import com.clipie.presentation.auth.components.AuthenticationButton
import com.clipie.presentation.auth.components.AuthenticationOutlinedButton
import com.clipie.presentation.auth.components.AuthenticationPasswordTextField
import com.clipie.presentation.auth.components.AuthenticationTextField
import com.clipie.presentation.auth.login_screen.LoginViewModel
import com.clipie.presentation.navigation.app.AppNavConstant
import com.clipie.presentation.navigation.app.AuthenticationNavConstant

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val viewModel = hiltViewModel<LoginViewModel>()
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(170.dp))
        Icon(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.padding(bottom = 30.dp)
        )
        AuthenticationTextField(
            value = email,
            onValueChange = { email = it },
            label = stringResource(R.string.email),
            imeAction = ImeAction.Next
        )
        AuthenticationPasswordTextField(
            value = password,
            onValueChange = { password = it },
            label = stringResource(R.string.password),
            imeAction = ImeAction.Done,
            modifier = Modifier.padding(top = 5.dp),
        )
        AuthenticationButton(
            modifier = Modifier
                .width(275.dp)
                .padding(10.dp),
            onClick = {
                navController.navigate(AppNavConstant.Main.route)
                viewModel.login(email, password) },
            text = stringResource(R.string.log_in)
        )
        TextButton(onClick = { }) {
            Text(
                text = stringResource(R.string.forgot_password),
                color = Color(0xFF1c2b33)
            )
        }
        AuthenticationOutlinedButton(
            modifier = Modifier
                .width(275.dp)
                .padding(top = 200.dp),
            onClick = { navController.navigate(AuthenticationNavConstant.Register.route) },
            text = stringResource(R.string.create_new_account)
        )
    }
}