package com.example.imagesearch.remote

import com.example.imagesearch.mapper.SearchRepository
import com.example.imagesearch.network.NetworkInterface
import com.example.imagesearch.recyclerview.Documents

class SearchRepositoryImpl(
    private val remoteDataSource: NetworkInterface
    ) : SearchRepository {
    override suspend fun getDataList(query: String) = remoteDataSource.getData(query = query, sort = "recency", page = 1, size = 80)

}