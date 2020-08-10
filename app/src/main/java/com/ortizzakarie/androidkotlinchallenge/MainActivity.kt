package com.ortizzakarie.androidkotlinchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ortizzakarie.androidkotlinchallenge.data.remote.api.ImageService
import com.ortizzakarie.androidkotlinchallenge.di.module.ImageApiModule
import kotlinx.coroutines.CoroutineScope

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}