package com.clipie.main.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.clipie.authentication.domain.models.User
import com.clipie.main.presentation.profile.components.*
import com.clipie.authentication.presentation.AuthenticationViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel = hiltViewModel<AuthenticationViewModel>()
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        ProfileScreenInformation(user = viewModel.getSession() ?: User())
        UserPanel()
    }
}



fun notificationFormat(notificationCount: Int): String {
    val outPut: String = when (notificationCount) {
        0 -> ""
        in 1..99 -> "  $notificationCount  "
        in 99..Int.MAX_VALUE -> "  99 + "
        else -> " Unknown "
    }
    return outPut
}