package com.josus.shoppingapp.data.local

import androidx.room.*
import com.josus.shoppingapp.data.local.entity.ProductEntity


@Dao
interface ProductListDao {

    //Inserting new products
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product:List<ProductEntity>)

    //getting all products
    @Query("SELECT * FROM productentity")
    suspend fun getProductList():List<ProductEntity>

    //deleting old products
    @Query("DELETE FROM productentity WHERE id IN(:productId)")
    suspend fun deleteProductList(productId:List<Int>)

    @Query("SELECT * FROM productentity ORDER BY price DESC")
    suspend fun getProductsByPriceDesc():List<ProductEntity>

    @Query("SELECT * FROM productentity ORDER BY title ASC")
     suspend fun getProductsByTitleAsc():List<ProductEntity>
}