package com.muradakhundov.jetflix.main.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.muradakhundov.jetflix.main.ui.viewmodel.HomeViewModel

@Composable
fun DetailScreen(navController: NavController, viewModel : HomeViewModel = hiltViewModel()) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Profile Screen")
    }

}