package com.example.customdi

interface LocalDataSource {
    fun getData(): String
}

class LocalDataSourceImpl : LocalDataSource {
    override fun getData(): String = "Local data source"
}