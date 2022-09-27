package com.example.wmtstore.data.models

data class Product(
    var id :String?= null,
    var name :String?= null,
    var size : String?= null,
    var image: String?= null,
    var price: Double?= 0.0,
    var seller: String?= null,
)