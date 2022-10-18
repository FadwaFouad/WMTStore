package com.example.wmtstore.data.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.wmtstore.data.favourite_provider.FavouriteProvider
import com.example.wmtstore.data.favourite_provider.FavouriteSharedPreferences
import com.example.wmtstore.data.firebase.ProductsDatasource
import com.example.wmtstore.data.models.Product

class FavouriteRepository (context: Context){
    private val favoriteProvider: FavouriteProvider = FavouriteSharedPreferences(context)
    private val productDatasource = ProductsDatasource ()

    fun isFavorite(productId: String): Boolean {
        return favoriteProvider.isFavorite(productId)
    }

    fun removeProduct(id: String) {
        favoriteProvider.removeFavorite(id)
    }

    fun addProduct(id: String) {
        favoriteProvider.addFavorite(id)
    }

    fun getAllFavorites(): MutableLiveData<List<String>> {
        return favoriteProvider.getFavoriteItems()
    }
    fun getProductFromIds(listOfIds: List<String>): MutableLiveData<List<Product>>{
        return productDatasource.getProductsFromIds(listOfIds)
    }
}