package com.example.and_practice.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.and_practice.data.model.ProductEntity

@Dao
interface ProductDao {

    // 전체 조회
    @Query("SELECT * FROM product")
    suspend fun getAll(): List<ProductEntity>

    // 위시리스트 조회, Room에서는 boolean 1/0으로 작성
    @Query("SELECT * FROM product WHERE isLiked = 1")
    suspend fun getWishlist(): List<ProductEntity>

    // BadgeType에 따른 조회
    @Query("SELECT * FROM product WHERE badgeType = :badgeType")
    suspend fun getByBadgeType(badgeType: String): List<ProductEntity>

    // 카테고리별 조회
    @Query("SELECT * FROM product WHERE categoryId = :categoryId")
    suspend fun getByCategory(categoryId: Int): List<ProductEntity>

    @Query("SELECT * FROM product WHERE id = :id LIMIT 1")
    suspend fun getById(id: Int): ProductEntity?

    // 데이터 삽입
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProduct(product: ProductEntity)

    // 데이터 업데이트
    @Update
    fun updateProduct(product: ProductEntity)

    // 데이터 삭제
    @Delete
    fun deleteProduct(product: ProductEntity)

}
