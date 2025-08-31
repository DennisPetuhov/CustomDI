package com.example.customdi.data

interface LocalDataSource {
    fun getData(): String
}

class LocalDataSourceImpl : LocalDataSource {
    override fun getData(): String = "Local data source"
}