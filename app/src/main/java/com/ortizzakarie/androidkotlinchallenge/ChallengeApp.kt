package com.ortizzakarie.androidkotlinchallenge

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@HiltAndroidApp
class ChallengeApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}