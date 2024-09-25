package com.example.wheatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.wheatherapp.db.AddUserDataBase
import com.example.wheatherapp.db.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideAddUserDataBase(@ApplicationContext context: Context): AddUserDataBase {
        return Room.databaseBuilder(context, AddUserDataBase::class.java, "USER_DB")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideUserDao(addUserDataBase: AddUserDataBase): UserDao {
        return addUserDataBase.getUserDao()
    }
}