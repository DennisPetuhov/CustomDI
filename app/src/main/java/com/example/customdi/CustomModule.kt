package com.example.customdi

import com.example.customdi.di.CustomDI
import com.example.customdi.di.Inject

class CustomModule {
    fun configure(customDI: CustomDI) {
        customDI.registerProvider(LocalDataSource::class) { LocalDataSourceImpl() }
        customDI.registerProvider(ServiceB::class) { ServiceB() }
        customDI.registerProvider(ServiceA::class) { ServiceA(customDI.getInstance()) }
    }
}

class ServiceB {
    fun hello() = println("Hello from B")
    fun msgB() = "Hello from B"
}

class ServiceA @Inject constructor(private val b: ServiceB) {
    fun run() = b.hello()
    fun msgB() = b.msgB()
}

class Config(val value: String)
