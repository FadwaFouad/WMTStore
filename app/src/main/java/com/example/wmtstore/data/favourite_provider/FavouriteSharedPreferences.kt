package com.example.wmtstore.data.favourite_provider

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData

class FavouriteSharedPreferences (context : Context): FavouriteProvider {
    private val favStorage : SharedPreferences = context.getSharedPreferences("FAVORITES", Context.MODE_PRIVATE )
    private  val favEditor : SharedPreferences.Editor = favStorage.edit()
    private val livedata = MutableLiveData<List<String>>()

    override fun addFavorite(productId: String) {
        favEditor.putString(productId, productId)
        favEditor.commit()

        notifyChange()
    }

    override fun removeFavorite(productId: String) {
        favEditor.remove(productId)
        favEditor.commit()

        notifyChange()
    }

    override fun isFavorite(productId: String): Boolean {
        val item : String? = favStorage.getString(productId,"")
        return !item.isNullOrEmpty()
    }

    override fun getFavoriteItems(): MutableLiveData<List<String>> {
        notifyChange()
        return livedata
    }

    private fun notifyChange (){
        livedata.value = favStorage.all.keys.toList()
    }
}