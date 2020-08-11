package com.ortizzakarie.androidkotlinchallenge.di.module

import android.app.Application
import com.ortizzakarie.androidkotlinchallenge.data.local.ImagesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class ImagesDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = ImagesDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideImageDao(database: ImagesDatabase) = database.getImageDao()

}