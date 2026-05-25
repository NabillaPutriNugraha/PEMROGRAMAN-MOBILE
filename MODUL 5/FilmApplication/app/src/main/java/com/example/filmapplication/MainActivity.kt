package com.example.filmapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.filmapplication.ui.navigation.AppNavigation
import com.example.filmapplication.ui.viewmodel.MovieViewModel
import com.example.filmapplication.ui.viewmodel.MovieViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                var errorMessage by remember { mutableStateOf<String?>(null) }
                var viewModel: MovieViewModel? by remember { mutableStateOf(null) }

                LaunchedEffect(Unit) {
                    try {
                        val appRepository = (application as FilmApplication).repository
                        viewModel = ViewModelProvider(
                            this@MainActivity,
                            MovieViewModelFactory(appRepository)
                        )[MovieViewModel::class.java]
                    } catch (e: Throwable) {
                        errorMessage = e.stackTraceToString()
                    }
                }

                if (errorMessage != null) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = "Aplikasi Mengalami Masalah Saat Dibuka:",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Red
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = errorMessage ?: "",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Black
                        )
                    }
                } else if (viewModel != null) {
                    AppNavigation(viewModel = viewModel!!)
                }
            }
        }
    }
}