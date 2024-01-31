package com.clipie.presentation.auth.components

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.clipie.R
import com.clipie.ui.theme.Background
import com.clipie.ui.theme.CursorTextField
import com.clipie.ui.theme.FocusedOnTextField
import com.clipie.ui.theme.TextField
import com.clipie.ui.theme.UnFocusedOnTextField

@Composable
fun AuthenticationPasswordTextField(
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
            .border(1.dp, focusedColor, RoundedCornerShape(25))
    )
}