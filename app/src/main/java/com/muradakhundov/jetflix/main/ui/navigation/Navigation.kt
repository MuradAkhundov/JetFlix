package com.muradakhundov.jetflix.main.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
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
import com.muradakhundov.jetflix.main.ui.bottomnav.BottomNavItem
import com.muradakhundov.jetflix.main.ui.bottomnav.NavigationBar
import com.muradakhundov.jetflix.main.ui.screen.ProfileScreen
import com.muradakhundov.jetflix.main.ui.screen.SearchScreen
import com.muradakhundov.jetflix.main.ui.theme.PrimaryDark

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = splashKey) {
        composable(onBoardingKey) { OnBoardingScreen(navController) }
        composable(splashKey) { SplashScreen(navController) }
        composable(registrationKey) { RegistrationScreen(navController) }
        composable(welcomeKey) { WelcomeScreen(navController) }
        composable(loginKey) { LoginScreen(navController) }
        composable(homeKey) {
            //TODO should ui design part to the screen
            val bottomNavController = rememberNavController()
            Scaffold(containerColor = PrimaryDark,
                contentColor = PrimaryDark,
                bottomBar = { NavigationBar(bottomNavController, BottomNavItem.values()) }
            ) {
                BottomNavigation(bottomNavController)
            }
        }
    }
}


@Composable
fun BottomNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) { HomeScreen(navController) }
        composable(BottomNavItem.Profile.route) { ProfileScreen(navController) }
        composable(BottomNavItem.Search.route) { SearchScreen(navController) }
    }
}
