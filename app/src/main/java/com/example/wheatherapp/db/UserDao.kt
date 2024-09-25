package com.example.wheatherapp.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Upsert
    suspend fun upsertUser(addUser: List<UserDetails>)

    @Query("SELECT * FROM UserDetails")
    fun getAddUserList(): Flow<List<UserDetails>>
}