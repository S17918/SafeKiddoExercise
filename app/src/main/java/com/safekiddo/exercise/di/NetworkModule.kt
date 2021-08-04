package com.safekiddo.exercise.di

import com.google.gson.GsonBuilder
import com.safekiddo.exercise.network.Service
import com.safekiddo.exercise.network.model.PostDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providePostMapper(): PostDtoMapper{
        return PostDtoMapper()
    }

    @Singleton
    @Provides
    fun provideService(): Service{
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(Service::class.java)
    }

}