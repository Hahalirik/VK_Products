package com.example.vk_products.data.api

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllProducts() = apiService.getAllProducts()

}