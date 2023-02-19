package com.rng.nycschools.di

import android.app.Application
import androidx.room.Room
import com.rng.nycschools.data.local.SchoolDatabase
import com.rng.nycschools.data.remote.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiInterface(): ApiInterface {
        return Retrofit.Builder().baseUrl(ApiInterface.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create()
    }

    @Provides
    @Singleton
    fun providesSchoolDatabase(app: Application): SchoolDatabase {
        return Room.databaseBuilder(app, SchoolDatabase::class.java, "school.db").build()
    }
}