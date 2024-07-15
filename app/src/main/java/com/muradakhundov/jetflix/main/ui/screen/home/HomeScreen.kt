package com.muradakhundov.jetflix.main.ui.screen.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.muradakhundov.jetflix.R
import com.muradakhundov.jetflix.common.util.Constants.Companion.KEY_DETAIL_NAVIGATION
import com.muradakhundov.jetflix.main.ui.screen.home.carouselview.MovieCarouselView
import com.muradakhundov.jetflix.main.ui.screen.home.categories.CategoriesRow
import com.muradakhundov.jetflix.main.ui.screen.home.movieitem.MovieCardItem
import com.muradakhundov.jetflix.main.ui.theme.JetFlixTheme
import com.muradakhundov.jetflix.main.ui.viewmodel.HomeViewModel


@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp, vertical = 8.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        UserProfileRow()
        Spacer(modifier = Modifier.height(10.dp))
        SearchBar(
            searchText = viewModel.searchText,
            onSearchTextChange = viewModel::onSearchTextChange,
            isSearching = viewModel.isSearching,
            onToggleSearch = viewModel::onToggleSearch
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    state.popularMovies?.let { movieResponse ->
                        MovieCarouselView(movies = movieResponse.results, navController = navController)
                    } ?: run {
                        Log.e("Flixjet", "null")
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Categories",
                        fontSize = 20.sp,
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                    CategoriesRow(
                        categories = listOf(
                            "All",
                            "Comedy",
                            "Animation",
                            "Documentary",
                            "Drama",
                            "Horror",
                            "Sci-Fi"
                        )
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Top Rated Movies",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
                        items(state.topRatedMovies?.results ?: emptyList()) { movie ->
                            MovieCardItem(
                                movie = movie,
                                navController = navController
                            )
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Upcoming Movies",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
                        items(state.upcomingMovies?.results ?: emptyList()) { movie ->
                            MovieCardItem(
                                movie = movie,
                                navController = navController
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
}
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
        Column {
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
            .padding(horizontal = 16.dp, vertical = 5.dp),
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