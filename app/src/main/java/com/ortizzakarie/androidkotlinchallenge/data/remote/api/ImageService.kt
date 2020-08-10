package com.ortizzakarie.androidkotlinchallenge.data.remote.api

import com.ortizzakarie.androidkotlinchallenge.model.Image
import retrofit2.Response
import retrofit2.http.GET

/**
 * Service to fetch the image JSON object from the [JSON_PLACEHOLDER_API_URL] endpoint.
 */

interface ImageService {

    @GET("/photos")
    suspend fun getImageJSON(): Response<List<Image>>

    companion object {
        const val JSON_PLACEHOLDER_API_URL = "https://jsonplaceholder.typicode.com"
    }
}