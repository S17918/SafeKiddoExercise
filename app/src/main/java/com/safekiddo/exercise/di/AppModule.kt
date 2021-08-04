package com.safekiddo.exercise.di

import android.content.Context
import com.safekiddo.exercise.presentation.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppliaction(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }
}