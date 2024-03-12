package com.clipie.main.presentation.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Divider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.clipie.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateBottomSheet(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onItemClick: (BottomSheetItem) -> Unit
) {
    val createSheetState = rememberModalBottomSheetState(true)
    val listOfBottomSheetItems: List<BottomSheetItem> = listOf(
        BottomSheetItem(
            text = stringResource(R.string.clip),
            imageVector = Icons.Outlined.PlayArrow
        ),
        BottomSheetItem(
            text = stringResource(R.string.post),
            imageVector = Icons.Outlined.PlayArrow
        ),
        BottomSheetItem(
            text = stringResource(R.string.story_highlight),
            imageVector = Icons.Outlined.PlayArrow
        ),
        BottomSheetItem(
            text = stringResource(R.string.live),
            imageVector = Icons.Outlined.PlayArrow
        ),
        BottomSheetItem(
            text = stringResource(R.string.made_for_you),
            imageVector = Icons.Outlined.PlayArrow
        ),
        BottomSheetItem(
            text = stringResource(R.string.fundraiser),
            imageVector = Icons.Outlined.PlayArrow
        )
    )
    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() },
        sheetState = createSheetState,
        modifier = modifier.height(550.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.create),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Divider()
        listOfBottomSheetItems.forEach { createBottomSheetItem ->
            CreateBottomSheetItem(
                bottomSheetItem = createBottomSheetItem,
                onItemClick = { onItemClick.invoke(it) }
            )
        }
    }
}

@Composable
fun CreateBottomSheetItem(
    bottomSheetItem: BottomSheetItem,
    onItemClick: (BottomSheetItem) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onItemClick.invoke(bottomSheetItem) }
            .fillMaxWidth()) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.size(40.dp)) {
            Icon(
                imageVector = bottomSheetItem.imageVector, contentDescription = null,
                modifier = Modifier.size(35.dp)
            )
        }
        Text(
            text = bottomSheetItem.text,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(vertical = 9.dp)
        )
    }
    Divider(modifier = Modifier.offset(42.dp))
}