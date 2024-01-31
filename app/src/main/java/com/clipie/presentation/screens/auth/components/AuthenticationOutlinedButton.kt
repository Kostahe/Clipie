package com.clipie.presentation.screens.auth.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.clipie.ui.theme.ButtonBackground
import com.clipie.ui.theme.OnButtonBackground


@Composable
fun AuthenticationOutlinedButton(
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