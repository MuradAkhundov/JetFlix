package com.muradakhundov.jetflix.entry.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.muradakhundov.jetflix.R
import com.muradakhundov.jetflix.common.util.Constants.Companion.KEY_DETAIL_NAVIGATION
import com.muradakhundov.jetflix.common.util.Constants.Companion.KEY_HOME_NAVIGATION
import com.muradakhundov.jetflix.common.util.Constants.Companion.KEY_ONBOARDING_NAVIGATION
import com.muradakhundov.jetflix.common.util.Constants.Companion.KEY_WELCOME_NAVIGATION
import com.muradakhundov.jetflix.entry.presentation.viewmodel.SharedEntryViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController, viewModel: SharedEntryViewModel = hiltViewModel()) {
    var visible by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(500)
        visible = false
        delay(500)
        if (viewModel.getUserSignedIn()) {
            navController.navigate(KEY_HOME_NAVIGATION) {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
            }
        } else if (viewModel.getOnboardingSeen()) {
            navController.navigate(KEY_WELCOME_NAVIGATION) {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
            }
        } else {
            navController.navigate(KEY_ONBOARDING_NAVIGATION) {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(animationSpec = tween(durationMillis = 500)),
            exit = fadeOut(animationSpec = tween(durationMillis = 500))
        ) {
            Image(
                painter = painterResource(id = R.drawable.jetflix),
                contentDescription = "app_icon",
                modifier = Modifier.clickable {
                        navController.navigate(KEY_DETAIL_NAVIGATION)
                    }

            )
        }
    }
}