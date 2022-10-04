package com.example.wmtstore.data.repository

import com.example.wmtstore.data.models.Product

object CartRepository {
    private val selectedProducts = mutableMapOf<Product, Int>()

    fun addToCart(product: Product) {
        if (selectedProducts.containsKey(product))
            increaseQuantity(product)
        else
            addProduct(product)

    }

    fun removeFromCart(product: Product) {
        selectedProducts.remove(product)
    }

    fun addProduct(product: Product) {
        selectedProducts[product] = 1
    }

    fun increaseQuantity(product: Product) {
        var quantity: Int = selectedProducts[product]!!
        selectedProducts[product] = ++quantity
    }

    fun decreaseQuantity(product: Product) {
        var quantity: Int = selectedProducts[product]!!
        selectedProducts[product] = --quantity
    }

    fun getprice(): Double {
        var price: Double = 0.0
        for (item in selectedProducts.keys) {
            price += item.price!!
        }
        return price
    }

    fun getSelectedProducts() = selectedProducts.toMap()

}