@file:Suppress("NAME_SHADOWING")

package com.clipie.main.presentation.profile.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.clipie.R
import com.clipie.authentication.domain.models.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onItemClick: (User) -> Unit,
    onAddAccountClick: () -> Unit,
    profilesList: List<User> = listOf()
) {
    val accountSheetState = rememberModalBottomSheetState(false)
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = accountSheetState,
        modifier = modifier
    ) {
        profilesList.forEach { user ->
            AccountBottomSheetItem(
                user = user,
                onAccountClicked = { user -> onItemClick.invoke(user) }
            )
        }
        Row(
            modifier = Modifier
                .clickable { onAddAccountClick() }
                .fillMaxWidth()
                .fillMaxHeight(0.1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(20.dp))
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = null,
                modifier = Modifier
                    .size(65.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Gray, CircleShape)
            )
            Text(
                text = stringResource(R.string.add_account),
                modifier = Modifier.padding(start = 20.dp),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun AccountBottomSheetItem(
    modifier: Modifier = Modifier,
    user: User,
    onAccountClicked: (User) -> Unit,
    selected: Boolean = false
) {
    Row(
        modifier = modifier.clickable { onAccountClicked.invoke(user) }.fillMaxWidth()
            .fillMaxHeight(0.1f),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(modifier = Modifier.width(20.dp))
        AsyncImage(
            model = user.imageUrl,
            contentDescription = stringResource(R.string.profile_picture, user.username),
            modifier = Modifier
                .size(65.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Gray, CircleShape)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = user.username,
            modifier = Modifier
                .padding(start = 20.dp)
                .weight(1f),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )
        // TODO(Don't forget to add functionality to RadioButtons so only one can be selected)
        RadioButton(
            selected = selected,
            onClick = null,
            modifier = Modifier.padding(end = 20.dp)
        )
    }
}