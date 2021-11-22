package com.josus.shoppingapp.data.remote.dto

import com.josus.shoppingapp.data.local.entity.ProductEntity


data class ProductDto(
    val id: Int,
    val title: String,
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
   // val rating: RatingDto

) {
    fun toProductEntity(): ProductEntity {
        return ProductEntity(
            id = id,
            title = title,
            category = category,
            description = description,
            image = image,
            price = price,
           // rating = rating.toRating()
        )
    }
}