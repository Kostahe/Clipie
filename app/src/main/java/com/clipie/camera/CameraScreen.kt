package com.clipie.camera

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

@Composable
fun CameraScreen(modifier: Modifier) {

    val cameraxPermissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO
    )
    val context = LocalContext.current
    val controller = remember {
        LifecycleCameraController(context).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE or
                CameraController.VIDEO_CAPTURE
            )
        }
    }
    fun hasRequiredPermissions():Boolean{
        return cameraxPermissions.all {
            ContextCompat.checkSelfPermission(
                context,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    if (!hasRequiredPermissions()){

        val activity: Activity = context as Activity
        ActivityCompat.requestPermissions(activity, cameraxPermissions, 0)
    }
    CameraPreview(controller =controller, modifier = Modifier.fillMaxSize())




}