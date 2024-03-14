package com.clipie.main.presentation.add.camera

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

@Composable
fun CameraScreen() {

    val cameraxPermissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO
    )
    val context = LocalContext.current
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




}