package com.example.appleappxmlupgrade.feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appleappxmlupgrade.feature.data.AppleSeries
import com.example.appleappxmlupgrade.feature.domain.Apple
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(private val appName: String) : ViewModel() {

    private val _appleState = MutableStateFlow<List<Apple>>(emptyList())
    val appleState: StateFlow<List<Apple>> = _appleState.asStateFlow()

    private val _navigateToDetail = MutableStateFlow<Int?>(null)
    val navigateToDetail: StateFlow<Int?> = _navigateToDetail.asStateFlow()

    init {
        Timber.d("CCTV: ViewModel berhasil diinisialisasi untuk: $appName")
        loadAppleData()
    }

    private fun loadAppleData() {
        viewModelScope.launch {
            try {
                val data = AppleSeries.getData()
                _appleState.value = data
                Timber.d("CCTV: Data Apple sebanyak ${data.size} item berhasil dimuat ke StateFlow")
            } catch (e: Exception) {
                Timber.e("CCTV: Terjadi kesalahan saat muat data: ${e.message}")
            }
        }
    }

    fun onDetailClicked(id: Int) {
        val selectedProduct = _appleState.value.find { it.id == id }

        Timber.d("CCTV: User memilih produk: ${selectedProduct?.name} (ID: $id)")

        _navigateToDetail.value = id
    }

    fun onNavigated() {
        _navigateToDetail.value = null
    }
}