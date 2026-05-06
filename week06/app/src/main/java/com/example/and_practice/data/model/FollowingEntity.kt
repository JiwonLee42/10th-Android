package com.example.and_practice.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "following")
data class FollowingEntity(
    @PrimaryKey
    val id: Long,
    val imageUrl: String
)
