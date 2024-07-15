package com.muradakhundov.jetflix.main.ui.screen

import android.graphics.Movie
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.*

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.muradakhundov.jetflix.R
import com.muradakhundov.jetflix.common.util.Constants
import com.muradakhundov.jetflix.main.data.model.movies.MovieResponse
import com.muradakhundov.jetflix.main.data.model.movies.Result
import com.muradakhundov.jetflix.main.ui.theme.JetFlixTheme
import com.muradakhundov.jetflix.main.ui.viewmodel.HomeViewModel
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    val state by viewModel.uiState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.size(10.dp, 20.dp))
        UserProfileRow()
        Spacer(modifier = Modifier.size(10.dp, 10.dp))
        SearchBar(
            searchText = viewModel.searchText,
            onSearchTextChange = viewModel::onSearchTextChange,
            isSearching = viewModel.isSearching,
            onToggleSearch = viewModel::onToggleSearch
        )
        if (state.isLoading) {
            CircularProgressIndicator()
            Log.e("Flixjet", "Loading...")
        }
        if (state.popularMovies != null) {
            state.popularMovies?.let { movieResponse ->
                CarouselView(movies = movieResponse.results)
            } ?: run {
                Log.e("Flixjet", "null")
            }
        } else {
            Log.e("Flixjet", "null")
        }
    }
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun CarouselView(movies: List<Result>) {
    val pagerState = rememberPagerState()

    HorizontalPager(
        count = movies.size,
        state = pagerState,
        modifier = Modifier
            // Remove fillMaxWidth() if not needed
            .padding(horizontal = 12.dp) // Adjust horizontal padding as needed
    ) { page ->
        val pageOffset = (pagerState.currentPage - page + pagerState.currentPageOffset).absoluteValue

        Box(
            modifier = Modifier
                .graphicsLayer {
                    val scale = lerp(0.55f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
                    scaleX = scale
                    scaleY = scale
                    alpha = lerp(0.35f, 1f, 1f - pageOffset.coerceIn(0f, 1f))
                }
                .padding(16.dp)
        ) {
            MovieCard(movies[page])
        }
    }
}


private fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return (start + (stop - start) * fraction)
}

@Composable
fun MovieCard(movie: Result) {
    Box(
        modifier = Modifier
            .width(300.dp)
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(30.dp))
    ) {
        Image(
            painter = rememberAsyncImagePainter("${Constants.IMAGE_URL}${movie.backdrop_path}"),
            contentDescription = movie.title,
            modifier = Modifier
                .height(200.dp)
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {
            Text(
                text = movie.title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = "On ${movie.release_date}",
                fontWeight = FontWeight.Light,
                fontSize = 14.sp,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserProfileRow(modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.default_pp),
            contentDescription = "Profile picture",
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape),
            alignment = Alignment.Center
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column() {
            Text(
                text = "Hello, Smith",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
            Text(
                text = "Let's stream your favorite movie",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White
            )
        }
    }


    var currentPage = 0
//
//    @Composable
//    fun CarouselIndicator(pageCount: Int, currentPage: Int) {
//        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
//            repeat(pageCount) { index ->
//                val isSelected = index == currentPage
//                val indicatorColor = if (isSelected) Color.Red else Color.Gray
//                val indicatorSize = if (isSelected) 16.dp else 8.dp
//                Box(
//                    modifier = Modifier
//                        .size(indicatorSize)
//                        .background(indicatorColor)
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//            }
//        }
//    }
//
//    Row(modifier = Modifier.fillMaxWidth()) {
//        HorizontalPager(state = ) { page ->
//            // Your carousel item content here
//        }
//        Spacer(modifier = Modifier.width(8.dp))
//        CarouselIndicator(pageCount = carouselItems.size, currentPage = currentPage)
//    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    isSearching: Boolean,
    onToggleSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = searchText,
        onValueChange = onSearchTextChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        placeholder = { Text("Search") },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        trailingIcon = {
            if (searchText.isNotEmpty()) {
                IconButton(onClick = { onSearchTextChange("") }) {
                    Icon(Icons.Default.Clear, contentDescription = null)
                }
            }
        },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = Color.Black,
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    JetFlixTheme {
        HomeScreen(navController = rememberNavController())
    }
}