package com.example.wmtstore.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wmtstore.data.models.Product
import com.example.wmtstore.data.repository.ProductsRepository

class HomeViewModel : ViewModel() {
    private val productsRepository = ProductsRepository()
    val products: MutableLiveData<List<Product>> = productsRepository.getProducts()

    fun getAllProducts(): MutableLiveData<List<Product>> {
        return products
    }

}