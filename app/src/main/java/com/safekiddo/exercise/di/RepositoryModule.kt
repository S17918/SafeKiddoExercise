package com.safekiddo.exercise.di

import com.safekiddo.exercise.network.Service
import com.safekiddo.exercise.network.model.PostDtoMapper
import com.safekiddo.exercise.persistance.PostDatabase
import com.safekiddo.exercise.repository.PostRepository
import com.safekiddo.exercise.repository.PostRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePostRepository(service: Service, mapper: PostDtoMapper, database: PostDatabase): PostRepository{
        return PostRepositoryImpl(service, mapper, database)
    }

}