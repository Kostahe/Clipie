package com.clipie.authentication.presentation.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.clipie.R
import com.clipie.authentication.domain.models.User
import com.clipie.authentication.presentation.AuthenticationViewModel
import com.clipie.authentication.presentation.components.AuthenticationButton
import com.clipie.authentication.presentation.components.AuthenticationPasswordTextField
import com.clipie.authentication.presentation.components.AuthenticationTextField
import com.clipie.authentication.presentation.navigation.AuthenticationNavConstant

@Composable
fun RegistrationScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: AuthenticationViewModel
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
    Scaffold { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = stringResource(R.string.create_new_account),
                modifier = Modifier.padding(end = 100.dp, top = 20.dp, start = 30.dp),
                fontSize = 40.sp,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(170.dp))
            AuthenticationTextField(
                value = email,
                onValueChange = { email = it },
                label = stringResource(R.string.email),
                imeAction = ImeAction.Next
            )
            AuthenticationTextField(
                value = username,
                onValueChange = { username = it },
                label = stringResource(R.string.username),
                imeAction = ImeAction.Done,
                modifier = modifier.padding(top = 5.dp),
            )
            AuthenticationPasswordTextField(
                value = password,
                onValueChange = { password = it },
                label = stringResource(R.string.password),
                imeAction = ImeAction.Done,
                modifier = modifier.padding(top = 5.dp),
            )


            AuthenticationButton(
                modifier = Modifier
                    .width(275.dp)
                    .padding(10.dp),
                onClick = {
                    viewModel.register(email , password, User(email = email, username = username))
                    navController.navigate(AuthenticationNavConstant.Login.route)
                },
                text = stringResource(id = R.string.create_account)
            )

            TextButton(
                onClick = { navController.navigateUp() },
                modifier = Modifier.padding(top = 200.dp)
            ) {
                Text(
                    text = stringResource(R.string.already_have_an_account),
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }

}
