package com.clipie.main.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.clipie.authentication.domain.models.User
import com.clipie.main.presentation.profile.components.*

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel
) {
    val user = viewModel.user ?: User()
    Column(
        modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        ProfileScreenInformation(user = user)
        UserPanel()
    }
}



