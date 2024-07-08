package com.muradakhundov.jetflix.main.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.muradakhundov.jetflix.common.util.Constants.Companion.homeKey
import com.muradakhundov.jetflix.common.util.Constants.Companion.loginKey
import com.muradakhundov.jetflix.common.util.Constants.Companion.onBoardingKey
import com.muradakhundov.jetflix.common.util.Constants.Companion.registrationKey
import com.muradakhundov.jetflix.common.util.Constants.Companion.splashKey
import com.muradakhundov.jetflix.common.util.Constants.Companion.welcomeKey
import com.muradakhundov.jetflix.main.ui.screen.HomeScreen
import com.muradakhundov.jetflix.authentication.presentation.auth.RegistrationScreen
import com.muradakhundov.jetflix.entry.presentation.ui.OnBoardingScreen
import com.muradakhundov.jetflix.entry.presentation.ui.SplashScreen
import com.muradakhundov.jetflix.main.ui.screen.WelcomeScreen
import com.muradakhundov.jetflix.authentication.presentation.auth.LoginScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = splashKey){

        composable(onBoardingKey){
            OnBoardingScreen(navController)
        }

        composable(splashKey){
            SplashScreen(navController)
        }

        composable(registrationKey){
            RegistrationScreen(navController)
        }

        composable(welcomeKey){
            WelcomeScreen(navController)
        }

        composable(loginKey){
            LoginScreen(navController = navController)
        }
        
        composable(homeKey){
            HomeScreen(navController = navController)
        }
    }

}