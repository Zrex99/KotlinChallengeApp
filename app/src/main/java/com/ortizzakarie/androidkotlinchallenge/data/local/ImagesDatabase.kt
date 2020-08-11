package com.ortizzakarie.androidkotlinchallenge.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ortizzakarie.androidkotlinchallenge.data.local.dao.ImageDao
import com.ortizzakarie.androidkotlinchallenge.model.Image

@Database(
    entities = [Image::class],
    version = 1
)
abstract class ImagesDatabase : RoomDatabase() {

    abstract fun getImageDao(): ImageDao

    companion object {
        private const val DB_NAME = "image_database"

        @Volatile
        private var INSTANCE: ImagesDatabase? = null

        fun getInstance(context: Context): ImagesDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ImagesDatabase::class.java,
                    DB_NAME
                ).build()

                INSTANCE = instance
                return instance
            }
        }

    }

}