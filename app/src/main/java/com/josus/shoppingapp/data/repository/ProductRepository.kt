package com.josus.shoppingapp.data.repository

import com.josus.shoppingapp.data.local.ProductListDatabase
import com.josus.shoppingapp.data.local.entity.ProductEntity
import com.josus.shoppingapp.data.remote.RetrofitInstance

class ProductRepository(
    private val db: ProductListDatabase
) {

    suspend fun getProductList() = db.productDao().getProductList()

    suspend fun deleteProductList(productIds: List<Int>) =
        db.productDao().deleteProductList(productIds)

    suspend fun insertProduct(productList: List<ProductEntity>) =
        db.productDao().insertProduct(productList)

    suspend fun getRemoteProductList() = RetrofitInstance.api.getProducts()

    suspend fun getProductByPrice() =
        db.productDao().getProductsByPriceDesc().map { it.toProduct() }

    suspend fun getProductByTitle() = db.productDao().getProductsByTitleAsc().map { it.toProduct() }
}
