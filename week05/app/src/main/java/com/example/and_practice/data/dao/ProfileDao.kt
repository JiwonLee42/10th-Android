package com.example.and_practice.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.and_practice.data.entity.ProfileEntity

@Dao
interface ProfileDao {

    @Query("SELECT * FROM profile")
    suspend fun getAll(): List<ProfileEntity>

    @Query("SELECT * FROM profile WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): ProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: ProfileEntity)

    @Update
    suspend fun updateProfile(profile: ProfileEntity)

    @Delete
    suspend fun deleteProfile(profile: ProfileEntity)

    @Query("DELETE FROM profile")
    suspend fun deleteAll()
}
