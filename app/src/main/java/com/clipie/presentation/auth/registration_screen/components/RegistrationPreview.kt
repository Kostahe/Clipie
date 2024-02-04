package com.clipie.presentation.auth.registration_screen.components

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clipie.R
import com.clipie.presentation.auth.components.AuthenticationButton
import com.clipie.presentation.auth.components.AuthenticationPasswordTextField
import com.clipie.presentation.auth.components.AuthenticationTextField
import com.clipie.presentation.navigation.app.AuthenticationNavConstant
import com.clipie.ui.theme.ClipieTheme

@Composable
@PreviewLightDark
fun RegistrationPreview() {
    ClipieTheme {
        Scaffold { innerPadding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
                    .padding(innerPadding)
            ) {
                Column(modifier = Modifier.padding(end = 100.dp, top = 40.dp, start = 50.dp),) {
                    Text(
                        text = stringResource(R.string.create_account),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = stringResource(R.string.please_enter_your_details),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                Spacer(modifier = Modifier.height(130.dp))
                AuthenticationTextField(
                    value = "test",
                    onValueChange = {},
                    label = stringResource(R.string.email),
                    imeAction = ImeAction.Next
                )
                AuthenticationTextField(
                    value = "test",
                    onValueChange = {},
                    label = stringResource(R.string.username),
                    imeAction = ImeAction.Done,
                    modifier = Modifier.padding(top = 10.dp),
                )
                AuthenticationPasswordTextField(
                    value = "",
                    onValueChange = {},
                    label = stringResource(R.string.password),
                    imeAction = ImeAction.Done,
                    modifier = Modifier.padding(top = 10.dp),
                )
                AuthenticationButton(
                    modifier = Modifier.width(275.dp)
                        .padding(top = 15.dp),
                    onClick = {},
                    text = stringResource(id = R.string.create_account)
                )
                TextButton(
                    onClick = { },
                    modifier = Modifier.padding(top = 200.dp)
                ) {
                    Text(
                        text = stringResource(R.string.already_have_an_account),
                    )
                }
            }
        }
    }
}