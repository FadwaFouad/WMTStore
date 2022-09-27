package com.example.wmtstore.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.wmtstore.data.firebase.ProductsDatasource
import com.example.wmtstore.data.models.Product

class ProductsRepository {

    fun getProducts (): MutableLiveData<List<Product>> {
        val productsDatasource = ProductsDatasource()
        return  productsDatasource.getProductsInfo()
    }
}