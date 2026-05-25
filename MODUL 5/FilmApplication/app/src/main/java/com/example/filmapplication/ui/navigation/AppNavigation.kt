package com.example.filmapplication.ui.navigation

import androidx.compose.runtime.*
import com.example.filmapplication.data.local.entity.MovieEntity
import com.example.filmapplication.ui.screen.DetailScreen
import com.example.filmapplication.ui.screen.HomeScreen
import com.example.filmapplication.ui.viewmodel.MovieViewModel

@Composable
fun AppNavigation(viewModel: MovieViewModel) {
    var selectedMovie by remember { mutableStateOf<MovieEntity?>(null) }

    if (selectedMovie == null) {
        HomeScreen(
            viewModel = viewModel,
            onMovieClick = { movie -> selectedMovie = movie }
        )
    } else {
        DetailScreen(
            movie = selectedMovie!!,
            onBackClick = { selectedMovie = null }
        )
    }
}