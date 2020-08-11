package com.ortizzakarie.androidkotlinchallenge.di.module

import android.app.Application
import com.ortizzakarie.androidkotlinchallenge.data.local.ImagesDatabase
import com.ortizzakarie.androidkotlinchallenge.data.local.dao.ImageDao
import com.ortizzakarie.androidkotlinchallenge.model.Image
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class ImagesDatabaseModule {

    /**
     * Provides the [ImagesDatabase]
     */

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = ImagesDatabase.getInstance(application)

    /**
     * Provides the [ImageDao] for accessing an [Image]
     */

    @Singleton
    @Provides
    fun provideImageDao(database: ImagesDatabase) = database.getImageDao()

}