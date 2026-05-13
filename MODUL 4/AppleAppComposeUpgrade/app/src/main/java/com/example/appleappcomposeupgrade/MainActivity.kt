package com.example.appleappcomposeupgrade

import android.content.pm.ApplicationInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.appleappcomposeupgrade.ui.navigation.AppNavigation
import com.example.appleappcomposeupgrade.ui.theme.AppleAppComposeUpgradeTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isDebuggable = (0 != (applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE))
        if (isDebuggable) {
            if (Timber.treeCount == 0) {
                Timber.plant(Timber.DebugTree())
            }
        }

        Timber.d("CCTV: MainActivity Berhasil Dijalankan")

        setContent {
            AppleAppComposeUpgradeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}