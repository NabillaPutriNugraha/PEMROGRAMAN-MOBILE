package com.example.appleappxml.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appleappxml.data.AppleSeries

class MainViewModel : ViewModel() {
    private val _appleList = MutableLiveData<List<AppleSeries>>()
    val appleList: LiveData<List<AppleSeries>> = _appleList

    init {
        loadAppleData()
    }

    private fun loadAppleData() {
        _appleList.value = AppleSeries.getData()
    }
}