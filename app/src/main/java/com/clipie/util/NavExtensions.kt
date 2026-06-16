package com.clipie.util

import androidx.navigation.NavHostController
import com.clipie.app.navigation.AppNavConstant

fun NavHostController.navigateToMainFromAuth() {
    this.navigate(AppNavConstant.Main.route) {
        popUpTo(AppNavConstant.Authentication.route) {
            inclusive = true
        }
    }
}