package com.example.vk_products.data

import com.example.vk_products.data.api.RemoteDataSource
import com.example.vk_products.data.api.models.Products
import com.example.vk_products.utils.BaseApiResponse
import com.example.vk_products.utils.NetworkResult
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): BaseApiResponse() {

    suspend fun getAllProducts(): NetworkResult<List<Products>> {
        return safeApiCall { remoteDataSource.getAllProducts() }
    }

}