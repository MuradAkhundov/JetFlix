package com.muradakhundov.jetflix.onboarding.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.muradakhundov.jetflix.R
import com.muradakhundov.jetflix.util.Constants.Companion.welcomeKey
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){

        Image(painter = painterResource(id = R.drawable.jetflix), contentDescription = "app_icon" )


    }
    LaunchedEffect(Unit) {
        delay(400)
        navController.navigate(welcomeKey)
    }
}