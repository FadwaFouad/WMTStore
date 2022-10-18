package com.example.wmtstore.ui.favourites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.wmtstore.data.models.Product
import com.example.wmtstore.data.repository.FavouriteRepository

class FavouritesViewModel (application: Application) : AndroidViewModel(application) {
    private val favRepository  = FavouriteRepository(application)

    fun isFavorite(productId: String): Boolean {
        return favRepository.isFavorite(productId)
    }

    fun removeProduct(id: String) {
        favRepository.removeProduct(id)
    }

    fun addProduct(id: String) {
        favRepository.addProduct(id)
    }

    fun getAllFavorites(): MutableLiveData<List<String>> {
        return favRepository.getAllFavorites()
    }

    fun getProductFromIds(listOfIds: List<String>): MutableLiveData<List<Product>> {
        return favRepository.getProductFromIds(listOfIds)
    }

}