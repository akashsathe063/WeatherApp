package com.example.wheatherapp.di

import android.content.Context
import androidx.room.Room
import com.example.wheatherapp.api.WeatherApi
import com.example.wheatherapp.db.AddUserDataBase
import com.example.wheatherapp.db.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideTweetyApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }
}

// https://api.openweathermap.org/data/2.5/onecall?lat=12.9082847623315&lon=77.65197822993314&units=imperial&appid=b143bb707b2ee117e62649b358207d3e  WeatherApi