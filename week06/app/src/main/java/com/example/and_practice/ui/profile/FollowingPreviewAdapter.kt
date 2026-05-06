package com.example.and_practice.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.and_practice.databinding.ItemFollowingPreviewBinding

class FollowingPreviewAdapter :
    ListAdapter<FollowingPreviewData, FollowingPreviewViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingPreviewViewHolder {
        val binding = ItemFollowingPreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FollowingPreviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowingPreviewViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<FollowingPreviewData>() {
            override fun areItemsTheSame(
                oldItem: FollowingPreviewData,
                newItem: FollowingPreviewData
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FollowingPreviewData,
                newItem: FollowingPreviewData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
