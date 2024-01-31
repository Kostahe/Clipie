package com.clipie.presentation.screens.auth.components

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.clipie.ui.theme.Background
import com.clipie.ui.theme.CursorTextField
import com.clipie.ui.theme.FocusedOnTextField
import com.clipie.ui.theme.TextField
import com.clipie.ui.theme.UnFocusedOnTextField

@Composable
fun AuthenticationTextField(
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




