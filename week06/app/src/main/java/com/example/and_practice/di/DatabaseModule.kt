package com.example.and_practice.di

import android.content.Context
import androidx.room.Room
import com.example.and_practice.data.local.dao.CategoryDao
import com.example.and_practice.data.local.dao.FollowingDao
import com.example.and_practice.data.local.dao.ProductDao
import com.example.and_practice.data.local.dao.ProfileDao
import com.example.and_practice.data.local.database.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val PRODUCT_DB_NAME = "nike_database"

    @Provides
    @Singleton
    fun provideProductDatabase(
        @ApplicationContext context: Context,
    ): ProductDatabase {
        return Room.databaseBuilder(
            context,
            ProductDatabase::class.java,
            PRODUCT_DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideProductDao(
        database: ProductDatabase,
    ): ProductDao = database.productDao()

    @Provides
    fun provideCategoryDao(
        database: ProductDatabase,
    ): CategoryDao = database.categoryDao()

    @Provides
    fun provideFollowingDao(
        database: ProductDatabase,
    ): FollowingDao = database.followingDao()

    @Provides
    fun provideProfileDao(
        database: ProductDatabase,
    ): ProfileDao = database.profileDao()
}
