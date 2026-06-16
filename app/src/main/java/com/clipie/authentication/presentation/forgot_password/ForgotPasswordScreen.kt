package com.clipie.authentication.presentation.forgot_password

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavHostController
import com.clipie.R
import com.clipie.authentication.presentation.AuthenticationViewModel
import com.clipie.authentication.presentation.components.AuthenticationButton
import com.clipie.authentication.presentation.components.AuthenticationTextField
import com.clipie.util.Resource

@Composable
fun ForgotPasswordScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: AuthenticationViewModel
) {
    val forgotPasswordState by viewModel.forgotPassword

    LaunchedEffect(forgotPasswordState) {
        if (forgotPasswordState is Resource.Success) {
            navController.popBackStack()
        }
    }

    DisposableEffect(Unit) {
        onDispose { viewModel.clearData() }
    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = stringResource(R.string.forgot_your_password))
        AuthenticationTextField(
            value = viewModel.email,
            onValueChange = viewModel::onEmailChange,
            imeAction = ImeAction.Done,
            label = stringResource(id = R.string.email)
        )
        AuthenticationButton(
            onClick = viewModel::forgotPassword,
            text = stringResource(R.string.recover_password)
        )
    }
}