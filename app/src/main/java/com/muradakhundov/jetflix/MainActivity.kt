package com.muradakhundov.jetflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.muradakhundov.jetflix.main.ui.navigation.Navigation
import com.muradakhundov.jetflix.main.ui.theme.JetFlixTheme
import com.muradakhundov.jetflix.main.ui.theme.PrimaryDark
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetFlixTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = PrimaryDark
                ) {
                    Navigation()
                }
            }
        }
    }
}
