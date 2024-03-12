package com.example.vk_products.data.api

import com.example.vk_products.data.api.models.Products
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/products")
    suspend fun getAllProducts(): Response<List<Products>>
}