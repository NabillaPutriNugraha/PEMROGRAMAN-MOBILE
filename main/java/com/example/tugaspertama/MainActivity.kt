package com.example.tugaspertama

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TampilanUtama()
        }
    }
}

@Composable
fun TampilanUtama() {
    var nama by remember { mutableStateOf("") }
    var hasilSapaan by remember { mutableStateOf("") }
    var modeGelap by remember { mutableStateOf(false) }

    val warnaPilihan = if (modeGelap) darkColorScheme() else lightColorScheme()

    MaterialTheme(colorScheme = warnaPilihan) {
        Surface(modifier = Modifier.fillMaxSize()) {

            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Mode Gelap")
                    Switch(
                        checked = modeGelap,
                        onCheckedChange = { modeGelap = it }
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = nama,
                    onValueChange = { nama = it },
                    label = { Text("Masukkan Nama") }
                )

                Spacer(modifier = Modifier.height(10.dp))

                Button(onClick = {
                    hasilSapaan = "Hello $nama!"
                }) {
                    Text("Sapa")
                }

                Spacer(modifier = Modifier.height(30.dp))

                Text(text = hasilSapaan, style = MaterialTheme.typography.headlineSmall)
            }
        }
    }
}