package com.example.tugasmobile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.tugasmobile.ui.theme.DescriptionStyle
import com.example.tugasmobile.ui.theme.TitleStyle
import com.example.tugasmobile.ui.theme.TugasMobileTheme
import kotlin.jvm.java
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {

    private var lifecycleStatus by mutableStateOf("Initializing...")
    private val TAG = "LifecycleLog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateLifecycle("onCreate")

        setContent {
            TugasMobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProfileScreen(
                        currentStatus = lifecycleStatus,
                        onNextClick = {
                            val intent = Intent(this, DetailActivity::class.java)
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }

    private fun updateLifecycle(methodName: String) {
        Log.d(TAG, "Method dipanggil: $methodName")
        lifecycleStatus = methodName
        Toast.makeText(this, "Lifecycle: $methodName", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() { super.onStart(); updateLifecycle("onStart") }
    override fun onResume() { super.onResume(); updateLifecycle("onResume") }
    override fun onPause() { super.onPause(); updateLifecycle("onPause") }
    override fun onStop() { super.onStop(); updateLifecycle("onStop") }
    override fun onRestart() { super.onRestart(); updateLifecycle("onRestart") }
    override fun onDestroy() { super.onDestroy(); updateLifecycle("onDestroy") }
}

@Composable
fun ProfileScreen(currentStatus: String, onNextClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.foto_profil),
            contentDescription = "Foto Mahasiswa",
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(16.dp)) // Syarat Shape Graphic
                .background(Color.LightGray)
        )

        Text(text = "Nabilla Putri Nugraha", style = TitleStyle)

        Text(text = "NIM: 2410817220009\nProdi: Teknologi Informasi", style = DescriptionStyle)

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Status Lifecycle: $currentStatus",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onNextClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(Icons.Default.Info, contentDescription = null)
            Spacer(Modifier.width(8.dp))
            Text("Lihat Detail Mahasiswa")
        }
    }
}