package com.clipie.presentation.screens.auth.login_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.clipie.R
import com.clipie.ui.theme.CursorTextField
import com.clipie.ui.theme.FocusedOnTextField
import com.clipie.ui.theme.Background
import com.clipie.ui.theme.ButtonBackground
import com.clipie.ui.theme.OnButtonBackground
import com.clipie.ui.theme.TextField
import com.clipie.ui.theme.UnFocusedOnTextField
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
        Spacer(modifier = Modifier.height(220.dp))
        Text(
            text = stringResource(R.string.clipie),
            fontFamily = lobsterFontFamily,
            fontSize = 60.sp,
            color = Color.Black
        )
        LoginScreenTextField(
            value = "",
            onValueChange = {},
            label = stringResource(R.string.email),
            imeAction = ImeAction.Next
        )
        LoginScreenPasswordTextField(
            value = "",
            onValueChange = {},
            label = stringResource(R.string.password),
            imeAction = ImeAction.Done,
            modifier = modifier.padding(top = 10.dp),
        )
        Row {
            Spacer(modifier = Modifier.width(140.dp))
            TextButton(onClick = {  }) {
                Text(
                    text = stringResource(R.string.forgot_password), color = Color.White
                )
            }
        }
        LoginScreenButton(
            modifier = Modifier.width(275.dp), onClick = {}, text = stringResource(R.string.log_in)
        )
        TextButton(onClick = { }) {
            Text(
                text = stringResource(R.string.forgot_password), color = Color(0xFF1c2b33)
            )
        }
        LoginScreenOutlineButton(
            modifier = Modifier.width(275.dp),
            onClick = { /*TODO*/ },
            text = stringResource(R.string.create_new_account)
        )
    }
}

@Composable
fun LoginScreenTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    imeAction: ImeAction,
    label: String,
) {
    var focusedColor: Color by remember {
        mutableStateOf(UnFocusedOnTextField)
    }
    TextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        label = {
            Text(
                text = label
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = TextField,
            focusedContainerColor = TextField,
            errorContainerColor = TextField,
            unfocusedIndicatorColor = Background,
            focusedIndicatorColor = Background,
            errorIndicatorColor = Background,
            cursorColor = CursorTextField,
            errorCursorColor = CursorTextField,
            focusedLabelColor = FocusedOnTextField,
            unfocusedLabelColor = UnFocusedOnTextField
        ),
        modifier = modifier
            .onFocusChanged {
                focusedColor = if (it.isFocused) {
                    FocusedOnTextField
                } else {
                    UnFocusedOnTextField
                }
            }
            .border(1.dp, focusedColor, RoundedCornerShape(25)),
    )
}

@Composable
fun LoginScreenPasswordTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    imeAction: ImeAction,
    label: String,
) {
    var showPassword by rememberSaveable {
        mutableStateOf(false)
    }
    var focusedColor: Color by remember {
        mutableStateOf(UnFocusedOnTextField)
    }
    val focusManager = LocalFocusManager.current

    TextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
        label = {
            Text(
                text = label,
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = TextField,
            focusedContainerColor = TextField,
            errorContainerColor = TextField,
            unfocusedIndicatorColor = Background,
            focusedIndicatorColor = Background,
            errorIndicatorColor = Background,
            cursorColor = CursorTextField,
            errorCursorColor = CursorTextField,
            focusedLabelColor = FocusedOnTextField,
            unfocusedLabelColor = UnFocusedOnTextField
        ),
        trailingIcon = {
            if (showPassword) {
                IconButton(onClick = { showPassword = false }) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_visibility_on),
                        contentDescription = stringResource(R.string.password_visible),
                        tint = focusedColor
                    )
                }
            } else {
                IconButton(onClick = { showPassword = true }) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_visibility_off),
                        contentDescription = stringResource(R.string.password_invisible),
                        tint = focusedColor
                    )
                }
            }
        },
        shape = RoundedCornerShape(50),
        modifier = modifier
            .onFocusChanged {
                focusedColor = if (it.isFocused) {
                    FocusedOnTextField
                } else {
                    UnFocusedOnTextField
                }
            }
            .border(1.dp, focusedColor, RoundedCornerShape(25)),
    )
}

@Composable
fun LoginScreenButton(
    modifier: Modifier = Modifier, onClick: () -> Unit, text: String
) {
    Button(
        onClick = onClick, colors = ButtonDefaults.buttonColors(
            containerColor = ButtonBackground, contentColor = OnButtonBackground
        ), shape = RoundedCornerShape(25),
        modifier = modifier
    ) {
        Text(
            text = text,
        )
    }
}

@Composable
fun LoginScreenOutlineButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String
) {
    OutlinedButton(
        onClick = onClick, colors = ButtonDefaults.outlinedButtonColors(
            contentColor = ButtonBackground,
            containerColor = OnButtonBackground
        ),
        modifier = modifier,
        shape = RoundedCornerShape(25),
        border = BorderStroke(1.dp, ButtonBackground)
    ) {
        Text(
            text = text
        )
    }
}