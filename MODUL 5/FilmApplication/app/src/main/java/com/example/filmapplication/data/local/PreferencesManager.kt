package com.example.filmapplication.data.local

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("film_app_prefs", Context.MODE_PRIVATE)

    fun saveLastSyncTime(timestamp: Long) {
        sharedPreferences.edit().putLong("last_sync_time", timestamp).apply()
    }

    fun getLastSyncTime(): Long {
        return sharedPreferences.getLong("last_sync_time", 0L)
    }
}