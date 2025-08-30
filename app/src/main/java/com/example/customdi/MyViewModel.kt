package com.example.customdi

import androidx.lifecycle.ViewModel
import com.example.customdi.di.Inject

class MyViewModel @Inject constructor(val localDataSource: LocalDataSource): ViewModel() {
    fun getData() = localDataSource.getData()
}