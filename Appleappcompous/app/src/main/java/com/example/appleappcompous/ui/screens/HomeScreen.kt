package com.example.appleappcompous.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.appleappcompous.data.AppleSeries
import com.example.appleappcompous.ui.components.AppleItemCard
import androidx.compose.foundation.lazy.items

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onNavigateToDetail: (Int) -> Unit) {
    val context = LocalContext.current
    val appleProducts = AppleSeries.getData()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Apple Series") })
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(appleProducts) { product ->
                AppleItemCard(
                    item = product,
                    onDetailClick = { onNavigateToDetail(product.id) },
                    onWebClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(product.webUrl))
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}