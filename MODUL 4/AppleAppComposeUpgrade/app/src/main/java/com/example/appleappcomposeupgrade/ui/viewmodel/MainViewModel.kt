package com.example.appleappcomposeupgrade.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appleappcomposeupgrade.data.model.Apple
import com.example.appleappcomposeupgrade.data.repository.AppleSeries
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(private val appName: String) : ViewModel() {

    private val _appleList = MutableStateFlow<List<Apple>>(emptyList())
    val appleList: StateFlow<List<Apple>> = _appleList.asStateFlow()

    private val _selectedAppleId = MutableStateFlow<Int?>(null)
    val selectedAppleId: StateFlow<Int?> = _selectedAppleId.asStateFlow()

    init {
        Timber.d("CCTV: ViewModel diinisialisasi untuk: $appName")
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            try {
                val data = AppleSeries.getData()
                _appleList.value = data
                Timber.d("CCTV: Data Apple sebanyak ${data.size} item berhasil dimuat ke StateFlow")
            } catch (e: Exception) {
                Timber.e("CCTV: Gagal memuat data: ${e.message}")
            }
        }
    }

    fun onDetailClicked(apple: Apple) {
        Timber.d("CCTV: Tombol Detail ditekan")
        Timber.d("CCTV: User memilih produk: ${apple.name} (ID: ${apple.id})")
        _selectedAppleId.value = apple.id
    }

    fun onNavigated() {
        _selectedAppleId.value = null
    }
}