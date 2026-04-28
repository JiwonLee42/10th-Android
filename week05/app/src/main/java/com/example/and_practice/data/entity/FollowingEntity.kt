package com.example.and_practice.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "following")
data class FollowingEntity(
    @PrimaryKey
    val id: Long,
    val imageUrl: String
)
