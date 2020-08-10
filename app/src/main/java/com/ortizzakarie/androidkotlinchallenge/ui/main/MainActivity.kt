package com.ortizzakarie.androidkotlinchallenge.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import com.ortizzakarie.androidkotlinchallenge.R
import com.ortizzakarie.androidkotlinchallenge.databinding.ActivityMainBinding
import com.ortizzakarie.androidkotlinchallenge.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Assign the MainViewModel class to the Main Activity
 * and the Activity Main Binding for the ViewBinding.
 */

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val mViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}