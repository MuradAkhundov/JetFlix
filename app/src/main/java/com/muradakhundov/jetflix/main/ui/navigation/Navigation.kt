package com.muradakhundov.jetflix.main.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.muradakhundov.jetflix.common.util.Constants.Companion.KEY_HOME_NAVIGATION
import com.muradakhundov.jetflix.common.util.Constants.Companion.KEY_LOG_IN_NAVIGATION
import com.muradakhundov.jetflix.common.util.Constants.Companion.KEY_ONBOARDING_NAVIGATION
import com.muradakhundov.jetflix.common.util.Constants.Companion.KEY_REGISTER_NAVIGATION
import com.muradakhundov.jetflix.common.util.Constants.Companion.KEY_SPLASH_NAVIGATION
import com.muradakhundov.jetflix.common.util.Constants.Companion.KEY_WELCOME_NAVIGATION
import com.muradakhundov.jetflix.main.ui.screen.home.HomeScreen
import com.muradakhundov.jetflix.authentication.ui.auth.RegistrationScreen
import com.muradakhundov.jetflix.entry.presentation.ui.OnBoardingScreen
import com.muradakhundov.jetflix.entry.presentation.ui.SplashScreen
import com.muradakhundov.jetflix.main.ui.screen.WelcomeScreen
import com.muradakhundov.jetflix.authentication.ui.auth.LoginScreen
import com.muradakhundov.jetflix.common.util.Constants.Companion.KEY_DETAIL_NAVIGATION
import com.muradakhundov.jetflix.main.ui.bottomnav.BottomNavItem
import com.muradakhundov.jetflix.main.ui.bottomnav.NavigationBar
import com.muradakhundov.jetflix.main.ui.screen.DetailScreen
import com.muradakhundov.jetflix.main.ui.screen.ProfileScreen
import com.muradakhundov.jetflix.main.ui.screen.SearchScreen
import com.muradakhundov.jetflix.main.ui.theme.PrimaryDark

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val bottomNavController = rememberNavController()

    NavHost(navController = navController, startDestination = KEY_SPLASH_NAVIGATION) {
        composable(KEY_ONBOARDING_NAVIGATION) { OnBoardingScreen(navController) }
        composable(KEY_SPLASH_NAVIGATION) { SplashScreen(navController) }
        composable(KEY_REGISTER_NAVIGATION) { RegistrationScreen(navController) }
        composable(KEY_WELCOME_NAVIGATION) { WelcomeScreen(navController) }
        composable(KEY_LOG_IN_NAVIGATION) { LoginScreen(navController) }
        composable(KEY_HOME_NAVIGATION) {
            //TODO should ui design part to the screen
            Scaffold(containerColor = PrimaryDark,
                contentColor = PrimaryDark,
                bottomBar = { NavigationBar(bottomNavController, BottomNavItem.values()) }
            ) {
                BottomNavigation(bottomNavController)
            }
        }
        composable(KEY_DETAIL_NAVIGATION) {
            DetailScreen(navController = bottomNavController)
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
