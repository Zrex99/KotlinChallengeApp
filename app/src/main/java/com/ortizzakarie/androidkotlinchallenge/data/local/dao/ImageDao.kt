package com.ortizzakarie.androidkotlinchallenge.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ortizzakarie.androidkotlinchallenge.model.Image
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    /**
     * Inserts [images] into the [Image.TABLE_NAME] table.
     * Duplicate values are overridden in the table.
     * @param images List of [Image]
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImages(images: List<Image>)

    /**
     * Deletes all images of from the [Image.TABLE_NAME] table.
     */
    @Query("DELETE FROM ${Image.TABLE_NAME}")
    fun deleteAllImages()

    /**
     * Retrieves the image from [Image.TABLE_NAME] table with the matching [imageId] id.
     * @param imageId Unique ID of [Image]
     * @return [Flow] of [Image] from database table.
     */
    @Query("SELECT * FROM ${Image.TABLE_NAME} WHERE id = :imageId")
    fun getImageById(imageId: Int): Flow<Image>

    /**
     * Retrieves all images from the [Image.TABLE_NAME] table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${Image.TABLE_NAME}")
    fun getAllImages(): Flow<List<Image>>
}