package com.example.appleappxmlupgrade.feature.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appleappxmlupgrade.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.plant(Timber.DebugTree())

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.d("CCTV: MainActivity berhasil dijalankan dan Timber aktif!")
    }
}