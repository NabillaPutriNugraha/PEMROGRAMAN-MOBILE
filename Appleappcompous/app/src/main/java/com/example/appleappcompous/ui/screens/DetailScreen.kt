package com.example.appleappcompous.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appleappcompous.data.AppleSeries

@Composable
fun DetailScreen(itemId: Int?) {
    val product = AppleSeries.getData().find { it.id == itemId }

    product?.let { item ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = item.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "Tahun Rilis: ${item.year}", fontSize = 16.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Spesifikasi Unggulan:", fontWeight = FontWeight.SemiBold)
            Text(text = "${item.specsLabel} ${item.specsValue}")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Deskripsi Lengkap:", fontWeight = FontWeight.SemiBold)
            Text(text = "Produk ini merupakan salah satu lini terbaik Apple yang dirilis pada tahun ${item.year} dengan teknologi ${item.specsValue}.")
        }
    }
}