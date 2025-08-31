package com.example

import android.app.Application
import com.example.customdi.di.Config
import com.example.customdi.data.LocalDataSource
import com.example.customdi.data.LocalDataSourceImpl
import com.example.customdi.di.CustomDI
import com.example.customdi.di.Provider

class App(): Application() {
    lateinit var customDI: CustomDI
        private set
    override fun onCreate() {
        super.onCreate()

        customDI = CustomDI()
        customDI.registerProvider(Config::class,
            Provider { Config("Global API Key from Application") })
        customDI.registerProvider(LocalDataSource::class, Provider { LocalDataSourceImpl() })
    }
}