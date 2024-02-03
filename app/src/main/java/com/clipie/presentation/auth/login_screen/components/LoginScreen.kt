package com.clipie.presentation.auth.login_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.clipie.R
import com.clipie.presentation.auth.AuthenticationViewModel
import com.clipie.presentation.auth.components.AuthenticationButton
import com.clipie.presentation.auth.components.AuthenticationOutlinedButton
import com.clipie.presentation.auth.components.AuthenticationPasswordTextField
import com.clipie.presentation.auth.components.AuthenticationTextField
import com.clipie.presentation.navigation.app.AppNavConstant
import com.clipie.presentation.navigation.app.AuthenticationNavConstant

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: AuthenticationViewModel
) {
    var email by rememberSaveable {
        mutableStateOf("")
    }
    var password by rememberSaveable {
        mutableStateOf("")
    }
    Scaffold { padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.fillMaxSize()
                .padding(padding)
        ) {
            Spacer(modifier = Modifier.height(170.dp))
            Icon(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = null,
                modifier = Modifier.padding(bottom = 30.dp),
                tint = MaterialTheme.colorScheme.primary
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
//                viewModel.login(email, password)
                    navController.navigate(AppNavConstant.Main.route) {
                        popUpTo(AppNavConstant.Authentication.route) {
                            inclusive = true
                        }
                    }
                },
                text = stringResource(R.string.log_in)
            )
            TextButton(onClick = { }) {
                Text(
                    text = stringResource(R.string.forgot_password),
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
}