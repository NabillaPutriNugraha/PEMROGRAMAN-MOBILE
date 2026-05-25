package com.example.filmapplication.data.repository

import com.example.filmapplication.data.local.entity.MovieEntity
import com.example.filmapplication.data.remote.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovies(category: String): Flow<Resource<List<MovieEntity>>>
}