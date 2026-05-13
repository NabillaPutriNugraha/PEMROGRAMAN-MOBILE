package com.example.appleappcomposeupgrade.ui.screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.appleappcomposeupgrade.ui.components.FeaturedCard
import com.example.appleappcomposeupgrade.ui.components.ProductItemCard
import com.example.appleappcomposeupgrade.ui.viewmodel.MainViewModel
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    onNavigateToDetail: (Int) -> Unit
) {
    val context = LocalContext.current
    val appleProducts by viewModel.appleList.collectAsStateWithLifecycle()
    val selectedId by viewModel.selectedAppleId.collectAsStateWithLifecycle()

    LaunchedEffect(selectedId) {
        selectedId?.let { id ->
            onNavigateToDetail(id)
            viewModel.onNavigated()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Apple Universe",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Featured Products",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                LazyRow(
                    contentPadding = PaddingValues(end = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(appleProducts) { product ->
                        FeaturedCard(
                            item = product,
                            onDetailClick = { viewModel.onDetailClicked(product) },
                            onWebClick = {
                                Timber.d("CCTV: Explicit Intent (Web) ditekan untuk ${product.name}")
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(product.webUrl))
                                context.startActivity(intent)
                            }
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "All Models",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            items(appleProducts, key = { it.id }) { product ->
                ProductItemCard(
                    item = product,
                    onDetailClick = { viewModel.onDetailClicked(product) },
                    onWebClick = {
                        Timber.d("CCTV: Explicit Intent (Web) ditekan untuk ${product.name}")
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(product.webUrl))
                        context.startActivity(intent)
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}