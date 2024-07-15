package com.muradakhundov.jetflix.main.ui.screen.home.carouselview

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.muradakhundov.jetflix.common.util.Constants
import com.muradakhundov.jetflix.common.util.Constants.Companion.KEY_DETAIL_NAVIGATION
import com.muradakhundov.jetflix.main.data.model.movies.Result
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


@ExperimentalPagerApi
@Composable
fun MovieCarouselView(movies: List<Result>,navController: NavController) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            count = movies.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) { page ->
            val pageOffset =
                ((pagerState.currentPage - page) + pagerState.currentPageOffset).absoluteValue

            CarouselMovieItem(
                movieId = movies[page].id,
                imageId = movies[page].backdrop_path,
                title = movies[page].title,
                releaseDate = movies[page].release_date,
                scale = lerp(0.85f, 1f, 1f - pageOffset.coerceIn(0f, 1f)),
                alpha = lerp(0.5f, 1f, 1f - pageOffset.coerceIn(0f, 1f)),
                navController = navController
            )
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = Color.Cyan,
            inactiveColor = Color.Gray,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp)
        )
    }

    LaunchedEffect(pagerState.currentPage) {
        coroutineScope.launch {
            pagerState.animateScrollToPage(pagerState.currentPage)
        }
    }
}

@Composable
fun CarouselMovieItem(
    movieId: Int,
    imageId: String,
    title: String,
    releaseDate: String,
    scale: Float,
    alpha: Float,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                this.alpha = alpha
            }
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                navController.navigate(KEY_DETAIL_NAVIGATION)
            }
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = "${Constants.IMAGE_URL}$imageId"),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = releaseDate,
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}