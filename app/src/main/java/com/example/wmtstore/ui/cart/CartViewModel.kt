package com.example.wmtstore.ui.cart

import androidx.lifecycle.ViewModel
import com.example.wmtstore.data.models.Product
import com.example.wmtstore.data.repository.CartRepository

class CartViewModel : ViewModel() {
   // val selectedProducts

    fun getProducts(): List<Product> {
        return CartRepository.getSelectedProducts().keys.toList()
    }
}