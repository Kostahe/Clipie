package com.clipie.ui

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.clipie.R

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Preview
@Composable
fun CustomClipieProgressBar() {
    val image = AnimatedImageVector.animatedVectorResource(id = R.drawable.app_logo)
    var atEnd by remember { mutableStateOf(false) }
    Image(painter = rememberAnimatedVectorPainter(image, atEnd), contentDescription = "")
}