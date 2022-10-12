package com.example.wmtstore.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wmtstore.data.models.Product

object CartRepository {
    private val selectedProducts = mutableMapOf<Product, Int>()
    val cartLiveData = MutableLiveData<MutableMap<Product, Int>>(selectedProducts)

    fun addToCart(product: Product) {
        if (selectedProducts.containsKey(product))
            increaseQuantity(product)
        else
            addProduct(product)
    }

    fun removeFromCart(product: Product) {
        selectedProducts.remove(product)
        notifyChange()
    }

    fun addProduct(product: Product) {
        selectedProducts[product] = 1
    }

    fun increaseQuantity(product: Product) {
        var quantity: Int = selectedProducts[product]!!
        selectedProducts[product] = ++quantity
        notifyChange()
    }

    fun decreaseQuantity(product: Product) {
            var quantity: Int = selectedProducts[product]!!
            selectedProducts[product] = --quantity
            notifyChange()
    }

    fun getPrice(): Double {
        var price: Double = 0.0
        for (item in selectedProducts.keys) {
            price += item.price!! * selectedProducts[item]!!
        }
        return price
    }

    fun clearCart (){
        selectedProducts.clear()
        notifyChange()
    }

    fun getSelectedProducts() = selectedProducts.toMap()
    fun getQuantity(product: Product): Int = selectedProducts[product]!!

    private fun notifyChange() {
        cartLiveData.value = selectedProducts
    }


}