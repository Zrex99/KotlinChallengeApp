package com.ortizzakarie.androidkotlinchallenge.ui.main.viewholder

import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.ortizzakarie.androidkotlinchallenge.R
import com.ortizzakarie.androidkotlinchallenge.databinding.ItemImageBinding
import com.ortizzakarie.androidkotlinchallenge.model.Image
import com.ortizzakarie.androidkotlinchallenge.ui.main.adapter.ImageListAdapter

/**
 * [RecyclerView.ViewHolder] implementation to inflate View for RecyclerView.
 * Only the [ImageView] that displays the [Image] will have an onClickListener for viewing the image on the device browser.
 * See [ImageListAdapter]
 */
class ImageViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(image: Image, onItemClicked: (Image) -> Unit) {
        binding.imageTitle.text = image.title
        binding.image.load(image.url) {
            placeholder(R.drawable.ic_photo)
            error(R.drawable.ic_broken_image)
        }

        //Set the onClickListener for the image only.
        binding.image.setOnClickListener {
            onItemClicked(image)
        }

        binding.imageTitle.setTextIsSelectable(true)
    }
}