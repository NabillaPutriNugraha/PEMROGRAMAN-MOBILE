package com.example.filmapplication.data.repository

import com.example.filmapplication.BuildConfig
import com.example.filmapplication.data.local.PreferencesManager
import com.example.filmapplication.data.local.dao.MovieDao
import com.example.filmapplication.data.local.entity.MovieEntity
import com.example.filmapplication.data.remote.ApiService
import com.example.filmapplication.data.remote.Resource
import com.example.filmapplication.data.remote.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

class MovieRepositoryImpl(
    private val apiService: ApiService,
    private val movieDao: MovieDao,
    private val preferencesManager: PreferencesManager
) : MovieRepository {

    private val apiKey = BuildConfig.TMDB_API_KEY

    override fun getMovies(category: String): Flow<Resource<List<MovieEntity>>> = flow {
        emit(Resource.Loading)
        Timber.d("CCTV: Memulai memuat data film untuk kategori [%s]", category)

        try {
            Timber.d("CCTV: Menghubungi Remote API TMDB...")
            val response = if (category == "popular") {
                apiService.getPopularMovies(apiKey)
            } else {
                apiService.getNowPlayingMovies(apiKey)
            }

            val movieEntities = response.results.map { dto -> dto.toEntity(category) }

            movieDao.deleteMoviesByCategory(category)
            movieDao.insertMovies(movieEntities)

            preferencesManager.saveLastSyncTime(System.currentTimeMillis())
            Timber.d("CCTV: Berhasil sinkronisasi ke Room. Waktu sync disimpan di SharedPreferences.")

        } catch (e: Exception) {
            Timber.e(e, "CCTV: Gagal mengambil data remote API. Mengaktifkan mode offline cache.")
            Timber.d("CCTV: Waktu sinkronisasi terakhir kali adalah: %d", preferencesManager.getLastSyncTime())
        }

        movieDao.getMoviesByCategory(category).map { Resource.Success(it) }.collect {
            emit(it)
        }
    }
}