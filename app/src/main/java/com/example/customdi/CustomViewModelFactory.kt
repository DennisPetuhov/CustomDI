package com.example.customdi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.customdi.di.CustomDI
import kotlin.reflect.KClass

class CustomViewModelFactory(private val customDI: CustomDI) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        return customDI.getInstance(modelClass)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return customDI.getInstance(modelClass.kotlin)
    }
}

