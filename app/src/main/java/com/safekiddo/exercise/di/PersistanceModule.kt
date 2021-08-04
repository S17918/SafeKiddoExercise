package com.safekiddo.exercise.di

import android.content.Context
import com.safekiddo.exercise.persistance.PostDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistanceModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): PostDatabase{
        return PostDatabase.getInstance(context)
    }
}