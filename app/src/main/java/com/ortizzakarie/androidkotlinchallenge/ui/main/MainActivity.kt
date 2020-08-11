package com.ortizzakarie.androidkotlinchallenge.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ortizzakarie.androidkotlinchallenge.R
import com.ortizzakarie.androidkotlinchallenge.databinding.ActivityMainBinding
import com.ortizzakarie.androidkotlinchallenge.model.Image
import com.ortizzakarie.androidkotlinchallenge.model.State
import com.ortizzakarie.androidkotlinchallenge.ui.base.BaseActivity
import com.ortizzakarie.androidkotlinchallenge.ui.main.adapter.ImageListAdapter
import com.ortizzakarie.androidkotlinchallenge.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Assign the MainViewModel class to the Main Activity
 * and the Activity Main Binding for the ViewBinding.
 */

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val mViewModel: MainViewModel by viewModels()

    private val mAdapter = ImageListAdapter(this::onItemClicked)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)

        //Initialize the RecyclerView
        mViewBinding.imagesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mAdapter
        }
        
        //Initialize the images
        initImages()
    }

    /**
     * Lifecycle & Override methods
     */

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    /*** Custom methods
     */
    private fun initImages() {
        mViewModel.imagesLiveData.observe(
            this,
            Observer { state ->
                when (state) {
                    is State.Loading -> showToast("Loading images")
                    is State.Success -> {
                        if (state.data.isNotEmpty()) {
                            mAdapter.submitList(state.data.toMutableList())
                            showToast("Loading done")
                        }
                    }
                    is State.Error -> {
                        showToast(state.message)
                    }
                }
            }
        )

        // If the state is not a success, then retry loading the images.
        if (mViewModel.imagesLiveData.value !is State.Success) {
            getImages()
        }
    }

    private fun getImages() {
        mViewModel.getImages()
    }


    /**
     * [onItemClicked] will take the user to view the image clicked on the device browser.
     * @param [image] is the Image that was clicked on by the user and the data object that holds the url for viewing.
     */
    private fun onItemClicked(image: Image) {
        val openUrl = Intent(Intent.ACTION_VIEW)
        openUrl.data = Uri.parse(image.url)
        startActivity(openUrl)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}