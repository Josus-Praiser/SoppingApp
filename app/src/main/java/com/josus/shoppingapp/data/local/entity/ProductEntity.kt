package com.josus.shoppingapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.josus.shoppingapp.data.model.Product
import com.josus.shoppingapp.data.model.Rating

@Entity
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
   // val rating: Rating
) {
    fun toProduct(): Product {
        return Product(
            id = id,
            title = title,
            category = category,
            description = description,
            image  = image,
            price = price,
           // rating = rating
        )
    }
}