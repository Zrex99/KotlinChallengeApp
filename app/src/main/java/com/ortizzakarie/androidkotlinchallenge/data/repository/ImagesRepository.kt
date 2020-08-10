package com.ortizzakarie.androidkotlinchallenge.data.repository


import androidx.annotation.MainThread
import com.ortizzakarie.androidkotlinchallenge.data.local.dao.ImageDao
import com.ortizzakarie.androidkotlinchallenge.data.remote.api.ImageService
import com.ortizzakarie.androidkotlinchallenge.model.Image
import com.ortizzakarie.androidkotlinchallenge.model.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Singleton repository for retrieving data from remote api and storing in database.
 */
@ExperimentalCoroutinesApi
@Singleton
class ImagesRepository @Inject constructor(
    private val imageDao: ImageDao,
    private val imageService: ImageService
) {

    /**
     * Retrieve the images from the network and store them in the database.
     * After storage, emit the data to the live observers.
     */
    fun getAllImages(): Flow<State<List<Image>>> {
        return object : NetworkBoundRepository<List<Image>, List<Image>>() {

            override suspend fun saveRemoteData(response: List<Image>) =
                imageDao.insertImages(response)

            override fun fetchFromLocal(): Flow<List<Image>> =
                imageDao.getAllImages()

            override suspend fun fetchFromRemote(): Response<List<Image>> =
                imageService.getImageJSON()
        }.asFlow().flowOn(Dispatchers.IO)
    }

    /**
     * Retrieves a image with the matching [imageId]
     * @param imageId Unique ID of [Image]
     * @return [Image] data retrieved from the database.
     */
    @MainThread
    fun getImageById(imageId: Int): Flow<Image> = imageDao.getImageById(imageId)
}