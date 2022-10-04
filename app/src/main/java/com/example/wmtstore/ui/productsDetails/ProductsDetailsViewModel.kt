package com.example.wmtstore.ui.productsDetails

import androidx.lifecycle.ViewModel
import com.example.wmtstore.data.models.Product
import com.example.wmtstore.data.repository.CartRepository

class ProductsDetailsViewModel () : ViewModel() {


    fun saveToCart( product : Product) {
        CartRepository.addToCart(product)
    }
}