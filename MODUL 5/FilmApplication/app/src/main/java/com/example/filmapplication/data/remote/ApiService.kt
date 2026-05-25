package com.example.filmapplication.data.remote

import com.example.filmapplication.data.remote.model.MovieResponse

interface ApiService {
    suspend fun getPopularMovies(apiKey: String): MovieResponse
    suspend fun getNowPlayingMovies(apiKey: String): MovieResponse
}