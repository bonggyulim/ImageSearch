package com.example.imagesearch.mapper

import com.example.imagesearch.recyclerview.Data

interface SearchRepository {
    suspend fun getDataList(query: String) : Data
}