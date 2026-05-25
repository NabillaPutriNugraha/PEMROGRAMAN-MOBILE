package com.example.filmapplication.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.filmapplication.data.local.entity.MovieEntity
import com.example.filmapplication.data.remote.Resource
import com.example.filmapplication.ui.components.MovieHorizontalItem
import com.example.filmapplication.ui.components.MovieVerticalItem
import com.example.filmapplication.ui.viewmodel.MovieViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: MovieViewModel,
    onMovieClick: (MovieEntity) -> Unit
) {
    val popularState by viewModel.popularMovies.collectAsState()
    val nowPlayingState by viewModel.nowPlayingMovies.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Film Cinema App", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item {
                Text(
                    text = "Popular Movies",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                )

                when (val state = popularState) {
                    is Resource.Loading -> Box(Modifier.fillMaxWidth().height(180.dp), contentAlignment = Alignment.Center) { CircularProgressIndicator() }
                    is Resource.Error -> Box(Modifier.fillMaxWidth().height(180.dp), contentAlignment = Alignment.Center) { Text("Gagal memuat data") }
                    is Resource.Success -> {
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(state.data) { movie ->
                                MovieHorizontalItem(movie = movie, onClick = { onMovieClick(movie) })
                            }
                        }
                    }
                }
            }
            item {
                Text(
                    text = "Now Playing in Cinema",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 12.dp)
                )
            }

            when (val state = nowPlayingState) {
                is Resource.Loading -> item { Box(Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) { CircularProgressIndicator() } }
                is Resource.Error -> item { Box(Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) { Text("Gagal memuat data") } }
                is Resource.Success -> {
                    items(state.data) { movie ->
                        MovieVerticalItem(movie = movie, onClick = { onMovieClick(movie) })
                    }
                }
            }
        }
    }
}