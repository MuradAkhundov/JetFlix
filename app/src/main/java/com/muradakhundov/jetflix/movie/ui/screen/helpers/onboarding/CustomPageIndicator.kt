package com.muradakhundov.jetflix.movie.ui.screen.helpers.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.muradakhundov.jetflix.movie.ui.theme.PrimaryAccent
import com.muradakhundov.jetflix.movie.ui.theme.PrimaryAccentTransparent
import kotlin.math.abs

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomPagerIndicator(pagerState: PagerState) {
    val baseWidth = 20.dp
    val offsetIntPart = pagerState.currentPageOffsetFraction.toInt()
    val offsetFractionalPart = pagerState.currentPageOffsetFraction - offsetIntPart
    val currentPage = pagerState.currentPage + offsetIntPart
    val targetPage =
        if (pagerState.currentPageOffsetFraction < 0) currentPage - 1 else currentPage + 1
    val currentPageWidth = (baseWidth.value * (1 + (1 - abs(offsetFractionalPart)) * 1.3)).dp
    val targetPageWidth = (baseWidth.value * (1 + abs(offsetFractionalPart) * 1.1)).dp

    Row(modifier = Modifier.clip(RoundedCornerShape(5.dp))) {
        repeat(pagerState.pageCount) { index ->
            val width = when (index) {
                currentPage -> currentPageWidth
                targetPage -> targetPageWidth
                else -> baseWidth
            }

            val color = if (index == currentPage) PrimaryAccent else PrimaryAccentTransparent

            Box(
                modifier = Modifier
                    .width(width)
                    .background(color)
                    .height(10.dp),
            )
            if (index != pagerState.pageCount - 1) {
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
    }
}
