package com.example.filmapplication

import android.app.Application
import com.example.filmapplication.data.local.AppDatabase
import com.example.filmapplication.data.local.PreferencesManager
import com.example.filmapplication.data.remote.ApiServiceImpl
import com.example.filmapplication.data.remote.client.KtorClient
import com.example.filmapplication.data.repository.MovieRepository
import com.example.filmapplication.data.repository.MovieRepositoryImpl
import timber.log.Timber

class FilmApplication : Application() {

    val repository: MovieRepository by lazy {
        val database = AppDatabase.getDatabase(this)
        val apiService = ApiServiceImpl(KtorClient.httpClient)
        val preferencesManager = PreferencesManager(this)
        MovieRepositoryImpl(apiService, database.movieDao(), preferencesManager)
    }

    override fun onCreate() {
        super.onCreate()
        if (Timber.treeCount == 0) {
            Timber.plant(Timber.DebugTree())
        }
    }
}