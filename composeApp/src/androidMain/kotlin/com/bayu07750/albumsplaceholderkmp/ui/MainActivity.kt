package com.bayu07750.albumsplaceholderkmp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.ui.Modifier
import com.bayu07750.albumsplaceholderkmp.MyApp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App(
                appContainer = (application as MyApp).appContainer,
                modifier = Modifier
                    .statusBarsPadding(),
            )
        }
    }
}
