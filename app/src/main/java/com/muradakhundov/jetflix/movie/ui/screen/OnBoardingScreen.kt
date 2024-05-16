package com.muradakhundov.jetflix.movie.ui.screen

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.muradakhundov.jetflix.util.Constants.Companion.splashKey
import com.muradakhundov.jetflix.movie.ui.screen.helpers.onboarding.CustomPagerIndicator
import com.muradakhundov.jetflix.movie.ui.screen.helpers.onboarding.PageContent
import com.muradakhundov.jetflix.movie.ui.theme.PrimaryAccent

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(navController: NavController) {
    val context = LocalContext.current
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
            if (it >= pagerState.pageCount - 1) {
                reachedLastPage = true
                navController.navigate(splashKey)
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


@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
}