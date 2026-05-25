package com.example.filmapplication.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.filmapplication.data.local.entity.MovieEntity
import com.example.filmapplication.data.remote.Resource
import com.example.filmapplication.data.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _popularMovies = MutableStateFlow<Resource<List<MovieEntity>>>(Resource.Loading)
    val popularMovies: StateFlow<Resource<List<MovieEntity>>> = _popularMovies.asStateFlow()

    private val _nowPlayingMovies = MutableStateFlow<Resource<List<MovieEntity>>>(Resource.Loading)
    val nowPlayingMovies: StateFlow<Resource<List<MovieEntity>>> = _nowPlayingMovies.asStateFlow()

    init {
        fetchAllMovies()
    }

    fun fetchAllMovies() {
        viewModelScope.launch {
            Timber.d("CCTV: ViewModel meminta data film terpopuler")
            repository.getMovies("popular").collect { result ->
                _popularMovies.value = result
            }
        }

        viewModelScope.launch {
            Timber.d("CCTV: ViewModel meminta data film sedang tayang")
            repository.getMovies("now_playing").collect { result ->
                _nowPlayingMovies.value = result
            }
        }
    }
}

class MovieViewModelFactory(private val repository: MovieRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel Class tidak dikenali")
    }
}