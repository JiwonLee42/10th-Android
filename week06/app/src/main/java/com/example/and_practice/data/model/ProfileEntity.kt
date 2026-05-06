package com.example.and_practice.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile")
data class ProfileEntity(
    @PrimaryKey
    val id: Long,
    val nickName: String,
    val profileImageUrl: String
)
