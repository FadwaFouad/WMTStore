package com.example.wmtstore.ui.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wmtstore.data.models.Product
import com.example.wmtstore.data.repository.CartRepository

class CartViewModel : ViewModel() {
    // val selectedProducts

    fun getProducts(): List<Product> {
        return CartRepository.getSelectedProducts().keys.toList()
    }

    fun getQuantity(product: Product): Int {
        return CartRepository.getQuantity(product)
    }

    fun removeFromCart(product: Product) {
        return CartRepository.removeFromCart(product)
    }

    fun increaseQuantity(product: Product) {
        return CartRepository.increaseQuantity(product)
    }

    fun decreaseQuantity(product: Product) {
        return CartRepository.decreaseQuantity(product)
    }

    fun deleteProduct(product: Product) = CartRepository.removeFromCart(product)


    fun getCarLiveData() : MutableLiveData<MutableMap<Product, Int>> = CartRepository.cartLiveData
    fun getTotalPrice() = CartRepository.getPrice()
    fun clearCart() = CartRepository.clearCart()

}