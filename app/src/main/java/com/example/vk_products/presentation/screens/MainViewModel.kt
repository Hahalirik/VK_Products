package com.example.vk_products.presentation.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_products.data.api.models.Products
import com.example.vk_products.domain.usecases.GetAllProductsUseCase
import com.example.vk_products.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase
): ViewModel() {

    private val _allProductsResponse = MutableLiveData<NetworkResult<List<Products>>>()
    val allProductsResponse: LiveData<NetworkResult<List<Products>>>
        get() = _allProductsResponse

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            getAllProductsUseCase.invoke().let {
                _allProductsResponse.value = it
            }
        }
    }

}