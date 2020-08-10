package com.ortizzakarie.androidkotlinchallenge.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ortizzakarie.androidkotlinchallenge.model.Image.Companion.TABLE_NAME
import com.squareup.moshi.JsonClass

/**
 * Data class for serialization and the database entity.
 */

@Entity(tableName = TABLE_NAME)
@JsonClass(generateAdapter = true)
data class Image(

    var albumId: Int? = 0,
    @PrimaryKey var id: Int? = 0,
    var title: String? = null,
    var url: String? = null,
    var thumbnailUrl: String? = null

) {
    companion object {
        const val TABLE_NAME = "json_images"
    }
}