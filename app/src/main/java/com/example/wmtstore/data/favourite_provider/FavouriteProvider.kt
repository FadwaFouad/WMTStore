package com.example.wmtstore.data.favourite_provider

import androidx.lifecycle.MutableLiveData

interface FavouriteProvider {
    fun addFavorite(productId: String)

    fun removeFavorite(productId: String)

    fun isFavorite(productId: String): Boolean

    fun getFavoriteItems(): MutableLiveData<List<String>>
}