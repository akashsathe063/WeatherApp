package com.example.wheatherapp.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserDetails::class], version = 1, exportSchema = false)
abstract class AddUserDataBase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}