package com.example.wheatherapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface UserDao {
    @Upsert
    suspend fun upsertUser(addUser: UserDetails)

    @Query("SELECT * FROM UserDetails")
    fun getAddUserList(): Flow<List<UserDetails>>

    @Delete
    suspend fun deleteUser(user:UserDetails)
}