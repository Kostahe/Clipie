package com.clipie.presentation.preview_parameter_providers

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.clipie.domain.model.User

class UserProvider : PreviewParameterProvider<User> {
    override val values: Sequence<User>
        get() = sequenceOf(
            User(username = "wcman007", bio = "XD")
        )
}