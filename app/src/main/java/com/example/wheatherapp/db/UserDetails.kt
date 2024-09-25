package com.example.wheatherapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDetails(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var firstName: String,
    var lastName: String,
    var email: String
)
