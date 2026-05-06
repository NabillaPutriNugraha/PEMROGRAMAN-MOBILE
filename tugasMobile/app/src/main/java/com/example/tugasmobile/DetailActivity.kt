package com.example.tugasmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tugasmobile.ui.theme.DescriptionStyle
import com.example.tugasmobile.ui.theme.TitleStyle
import com.example.tugasmobile.ui.theme.TugasMobileTheme

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TugasMobileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Detail Mahasiswa",
                            style = TitleStyle
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Hobi: Coding & Gaming\n" +
                                    "Alamat: Jl. Raya Teknologi No. 10\n" +
                                    "Email: mahasiswa@kampus.ac.id",
                            style = DescriptionStyle,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        Button(
                            onClick = { finish() },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(Icons.Default.ArrowBack, contentDescription = null)
                            Spacer(Modifier.width(8.dp))
                            Text("Kembali")
                        }
                    }
                }
            }
        }
    }
}