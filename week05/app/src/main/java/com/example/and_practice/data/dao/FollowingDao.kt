package com.example.and_practice.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.and_practice.data.entity.FollowingEntity

@Dao
interface FollowingDao {

    @Query("SELECT * FROM `following`")
    suspend fun getAll(): List<FollowingEntity>

    @Query("SELECT * FROM `following` WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): FollowingEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFollowing(following: FollowingEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(followings: List<FollowingEntity>)

    @Update
    suspend fun updateFollowing(following: FollowingEntity)

    @Delete
    suspend fun deleteFollowing(following: FollowingEntity)

    @Query("DELETE FROM `following`")
    suspend fun deleteAll()
}
