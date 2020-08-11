package com.ortizzakarie.androidkotlinchallenge.di.module

import com.ortizzakarie.androidkotlinchallenge.data.remote.api.ImageService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class ImageApiModule {



    @Singleton
    @Provides
    fun provideRetrofitService(): ImageService = Retrofit.Builder()
        .baseUrl(ImageService.JSON_PLACEHOLDER_API_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        )
        .build()
        .create(ImageService::class.java)
}