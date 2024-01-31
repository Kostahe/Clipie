package com.clipie.presentation.auth.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.clipie.ui.theme.ButtonBackground
import com.clipie.ui.theme.OnButtonBackground

@Composable
fun AuthenticationButton(
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