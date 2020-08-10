package com.ortizzakarie.androidkotlinchallenge.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ortizzakarie.androidkotlinchallenge.data.repository.ImagesRepository
import com.ortizzakarie.androidkotlinchallenge.model.Image
import com.ortizzakarie.androidkotlinchallenge.model.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * ViewModel for [MainActivity]
 */
@ExperimentalCoroutinesApi
class MainViewModel @ViewModelInject constructor(private val imagesRepository: ImagesRepository) :
    ViewModel() {

    private val _imagesLiveData = MutableLiveData<State<List<Image>>>()

    val imagesLiveData: LiveData<State<List<Image>>>
        get() = _imagesLiveData

    fun getImages() {
        viewModelScope.launch {
            imagesRepository.getAllImages().collect {
              _imagesLiveData.value = it
            }
        }
    }
}