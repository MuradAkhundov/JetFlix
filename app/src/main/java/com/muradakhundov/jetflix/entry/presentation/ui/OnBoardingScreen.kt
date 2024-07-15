package com.muradakhundov.jetflix.entry.presentation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.muradakhundov.jetflix.main.ui.screen.helpers.onboarding.CustomPagerIndicator
import com.muradakhundov.jetflix.main.ui.screen.helpers.onboarding.PageContent
import com.muradakhundov.jetflix.main.ui.theme.PrimaryAccent
import com.muradakhundov.jetflix.entry.presentation.viewmodel.SharedEntryViewModel
import com.muradakhundov.jetflix.common.util.Constants.Companion.KEY_WELCOME_NAVIGATION

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(navController: NavController,viewModel: SharedEntryViewModel = hiltViewModel()) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    var reachedLastPage = false
    var nextPage by remember { mutableStateOf<Int?>(null) }

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(state = pagerState) { page ->
            PageContent(page = page)
        }
        Row(
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CustomPagerIndicator(pagerState = pagerState)
            Spacer(modifier = Modifier.size(140.dp, 0.dp))
            SquareFloatingActionButton(
                onClick = {
                    nextPage = pagerState.currentPage + 1
                },
                containerColor = PrimaryAccent,
                cornerRadius = 5.dp
            )
        }
    }

    LaunchedEffect(nextPage) {
        nextPage?.let {
            pagerState.animateScrollToPage(it)
            if (it >= pagerState.pageCount) {
                reachedLastPage = true
                navController.navigate(KEY_WELCOME_NAVIGATION){
                    popUpTo(navController.graph.startDestinationId) {
                        inclusive = true
                    }
                }
                viewModel.setOnboardingSeen()
            }
            nextPage = null
        }
    }
}


@Composable
fun SquareFloatingActionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    containerColor: Color,
    cornerRadius: Dp
) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = containerColor,
        modifier = modifier
            .size(56.dp)
            .clip(RoundedCornerShape(cornerRadius))
    ) {
        Icon(Icons.Filled.KeyboardArrowRight, contentDescription = null)
    }
}