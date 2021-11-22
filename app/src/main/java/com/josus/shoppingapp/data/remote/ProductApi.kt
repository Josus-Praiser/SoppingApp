package com.josus.shoppingapp.data.remote


import com.josus.shoppingapp.data.remote.dto.ProductDto
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    //https://fakestoreapi.com/products

    @GET("/products")
    suspend fun getProducts(): List<ProductDto>
}