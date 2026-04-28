package com.example.and_practice.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.and_practice.data.entity.CategoryEntity

@Dao
interface CategoryDao {

    // 새 카테고리 삽입
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCategory(category: CategoryEntity)

    // 카테고리 정보 수정
    @Update
    fun updateCategory(category: CategoryEntity)

    // 특정 카테고리 삭제
    @Delete
    fun deleteCategory(category: CategoryEntity)

    @Query("SELECT * FROM Category")
    fun getAllCategories(): List<CategoryEntity>



}
