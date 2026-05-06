package com.example.and_practice.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.and_practice.data.local.dao.CategoryDao
import com.example.and_practice.data.local.dao.FollowingDao
import com.example.and_practice.data.local.dao.ProfileDao
import com.example.and_practice.data.local.dao.ProductDao
import com.example.and_practice.data.model.CategoryEntity
import com.example.and_practice.data.model.FollowingEntity
import com.example.and_practice.data.model.ProfileEntity
import com.example.and_practice.data.model.ProductEntity

@Database(
    entities = [ProductEntity::class, CategoryEntity::class, FollowingEntity::class, ProfileEntity::class],
    version = 4,
    exportSchema = false
)
abstract class ProductDatabase : RoomDatabase(){

    abstract fun productDao() : ProductDao
    abstract fun categoryDao() : CategoryDao
    abstract fun followingDao() : FollowingDao
    abstract fun profileDao() : ProfileDao

    companion object {
        @Volatile
        private var INSTANCE: ProductDatabase? = null

        fun getInstance(context: Context) : ProductDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java,
                    "nike_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}
