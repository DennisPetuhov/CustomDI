package com.example.customdi.presentation

import androidx.lifecycle.ViewModel
import com.example.customdi.data.LocalDataSource
import com.example.customdi.di.Inject

class MyViewModel @Inject constructor(val localDataSource: LocalDataSource): ViewModel() {
    fun getData() = localDataSource.getData()
}