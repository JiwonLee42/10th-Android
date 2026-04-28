package com.example.and_practice.data.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "product",
    indices = [Index(value = ["name"], unique = true)]
)
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val content: String,
    val color: Int,
    val imageUrl: String,
    val price: Int,
    val isLiked: Boolean,
    val categoryId: Int,
    val badgeType: String = "NONE"
)
