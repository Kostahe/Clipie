package com.clipie.authentication.presentation.forgot_password

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavHostController
import com.clipie.R
import com.clipie.authentication.presentation.AuthenticationViewModel
import com.clipie.authentication.presentation.components.AuthenticationButton
import com.clipie.authentication.presentation.components.AuthenticationTextField

@Composable
fun ForgotPasswordScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: AuthenticationViewModel
) {
    var email by rememberSaveable {
        mutableStateOf("")
    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = stringResource(R.string.forgot_your_password))
        AuthenticationTextField(
            value = email,
            onValueChange = { email = it },
            imeAction = ImeAction.Done,
            label = stringResource(id = R.string.email)
        )
        AuthenticationButton(
            onClick = { viewModel.forgotPassword(email) },
            text = stringResource(R.string.recover_password)
        )
    }
}