package com.example.vk_products.domain.usecases

import com.example.vk_products.data.ApiRepository
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {

    suspend fun invoke() = apiRepository.getAllProducts()

}