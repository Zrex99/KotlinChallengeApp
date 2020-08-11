package com.ortizzakarie.androidkotlinchallenge.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ortizzakarie.androidkotlinchallenge.databinding.ItemImageBinding
import com.ortizzakarie.androidkotlinchallenge.model.Image
import com.ortizzakarie.androidkotlinchallenge.ui.main.viewholder.ImageViewHolder

/**
 * Adapter class [RecyclerView.Adapter] for [RecyclerView] which binds [Image] along with [ImageViewHolder]
 * @param onItemClicked which will receive callback when item is clicked.
 */
class ImageListAdapter(
    private val onItemClicked: (Image) -> Unit
) : ListAdapter<Image, ImageViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ImageViewHolder(
        ItemImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    interface OnItemClickListener {
        fun onItemClicked(image: Image)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Image>() {
            override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean =
                oldItem == newItem
        }
    }
}