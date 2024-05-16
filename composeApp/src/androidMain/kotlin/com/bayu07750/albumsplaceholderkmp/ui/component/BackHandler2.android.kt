package com.bayu07750.albumsplaceholderkmp.ui.component

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

@Composable
actual fun BackHandler2(enabled: Boolean, onBack: () -> Unit) {
    BackHandler(enabled) {
        onBack()
    }
}