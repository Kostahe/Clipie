@file:Suppress("NAME_SHADOWING")

package com.clipie.presentation.main.profile_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.clipie.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onItemClick: (BottomSheetItem) -> Unit
) {
    val listSheetState = rememberModalBottomSheetState(true)
    val listOfBottomSheetItem: List<BottomSheetItem> = listOf(
        BottomSheetItem(
            text = stringResource(R.string.settings_and_privacy),
            imageVector = Icons.Outlined.Settings
        ),
        BottomSheetItem(
            text = stringResource(id = R.string.your_activity),
            imageVector = Icons.Outlined.Info
        ),
        BottomSheetItem(
            text = stringResource(R.string.archived),
            imageVector = Icons.Outlined.Star,
        ),
        BottomSheetItem(
            text = stringResource(R.string.share_account),
            imageVector = Icons.Outlined.Share,
        ),
        BottomSheetItem(
            text = stringResource(R.string.saved),
            imageVector = Icons.Outlined.Star,
        ),
        BottomSheetItem(
            text = stringResource(R.string.supervision),
            imageVector = Icons.Outlined.Face,
        ),
        BottomSheetItem(
            text = stringResource(R.string.verification),
            imageVector = Icons.Outlined.CheckCircle,
        ),
        BottomSheetItem(
            text = stringResource(R.string.close_friends),
            imageVector = Icons.Outlined.Person,
        ),
        BottomSheetItem(
            text = stringResource(R.string.favorites),
            imageVector = Icons.Outlined.Star,
        )
    )
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = listSheetState,
        modifier = modifier.fillMaxWidth()
    ) {
        listOfBottomSheetItem.forEach { bottomSheetItem ->
            CreateBottomSheetItem(
                bottomSheetItem = bottomSheetItem,
                onItemClick = { bottomSheetItem -> onItemClick.invoke(bottomSheetItem) }
            )
        }
    }
}

@Composable
fun CreateBottomSheetItem(
    modifier: Modifier = Modifier,
    bottomSheetItem: BottomSheetItem,
    onItemClick: (BottomSheetItem) -> Unit
) {
    Row(modifier = modifier.clickable { onItemClick.invoke(bottomSheetItem) }) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(50.dp)
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = bottomSheetItem.imageVector,
                contentDescription = null,
                Modifier.size(30.dp)
            )
        }
        Text(
            text = bottomSheetItem.text,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}