package com.josus.shoppingapp.data.remote.dto

import com.josus.shoppingapp.data.model.Rating

data class RatingDto(
    val count: Int,
    val rate: Double
) {
    fun toRating(): Rating {
        return Rating(
            count = count,
            rate = rate
        )
    }
}
