package com.clipie.main.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.clipie.R
import com.clipie.authentication.domain.models.User

@Composable
fun ProfileScreenInformation(
    user: User,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(horizontal = 10.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = user.imageUrl,
                placeholder = painterResource(id = R.drawable.temp_acc_pfp),
                contentDescription = null,
                modifier = Modifier.size(90.dp)
                    .clip(RoundedCornerShape(60.dp))
            )
            ProfileInformationItem(count = user.totalPosts, label = stringResource(R.string.posts))
            ProfileInformationItem(count = user.followers.size.toString(), label = stringResource(R.string.followers))
            ProfileInformationItem(count = user.following.size.toString(), label = stringResource(R.string.following))
        }
        Column {
            Text(text = user.username)
            Text(text = user.bio)
        }
    }
}

@Composable
fun ProfileInformationItem(
    modifier: Modifier = Modifier,
    count: String,
    label: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = count)
        Text(text = label)
    }
}