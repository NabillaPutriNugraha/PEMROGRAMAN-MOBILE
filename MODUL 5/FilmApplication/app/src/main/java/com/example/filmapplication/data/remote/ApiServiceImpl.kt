package com.example.filmapplication.data.remote

import com.example.filmapplication.data.remote.model.MovieResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ApiServiceImpl(private val client: HttpClient) : ApiService {

    private val baseUrl = "https://api.themoviedb.org/3"

    override suspend fun getPopularMovies(apiKey: String): MovieResponse {
        return client.get("$baseUrl/movie/popular") {
            parameter("api_key", apiKey)
        }.body<MovieResponse>()
    }

    override suspend fun getNowPlayingMovies(apiKey: String): MovieResponse {
        return client.get("$baseUrl/movie/now_playing") {
            parameter("api_key", apiKey)
        }.body<MovieResponse>()
    }
}