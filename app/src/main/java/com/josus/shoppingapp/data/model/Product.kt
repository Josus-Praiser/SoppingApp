package com.josus.shoppingapp.data.model

data class Product(
    val id: Int,
    val title: String,
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
    //val rating: Rating
)