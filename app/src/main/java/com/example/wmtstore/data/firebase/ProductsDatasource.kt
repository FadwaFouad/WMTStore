package com.example.wmtstore.data.firebase

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.wmtstore.data.models.Product
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ProductsDatasource {

    // get reference to firestore database
    val db = Firebase.firestore

    // Create fun that fetch products from database
    fun getProductsInfo(): MutableLiveData<List<Product>> {
        val productLiveData = MutableLiveData<List<Product>>()

        db.collection("products")
            .get()
            .addOnSuccessListener { documents ->
                val listOfProducts: List<Product> = documents.toObjects(Product::class.java)
                productLiveData.value = listOfProducts
            }
            .addOnFailureListener { error ->
                Log.e("Firebase Error", error.message.toString())
            }
        return productLiveData
    }

    fun getProductsFromIds(productsIds: List<String>): MutableLiveData<List<Product>> {
        val productLivedata = MutableLiveData<List<Product>>()
        if (productsIds.isEmpty()) return productLivedata

        db.collection("products")
            .whereIn("id", productsIds)
            .get()
            .addOnSuccessListener { documents ->
                val listOfProducts: List<Product> = documents.toObjects(Product::class.java)
                productLivedata.value = listOfProducts
            }
            .addOnFailureListener { error ->
                Log.e("Firebase Error", error.message.toString())
            }

        return productLivedata
    }

}