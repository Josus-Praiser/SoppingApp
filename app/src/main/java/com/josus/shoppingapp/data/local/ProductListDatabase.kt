package com.josus.shoppingapp.data.local

import android.content.Context
import androidx.room.*
import com.josus.shoppingapp.data.local.entity.ProductEntity


@Database(entities = [ProductEntity::class], version = 1)
//@TypeConverters(Converters::class)
abstract class ProductListDatabase : RoomDatabase() {
    abstract fun productDao(): ProductListDao

    companion object{
        @Volatile
        private var instance: ProductListDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ProductListDatabase::class.java,
                "article_db.db"
            )
                .build()
    }
}