package com.example.and_practice.presentation.profile

import androidx.recyclerview.widget.RecyclerView
import coil3.load
import coil3.request.crossfade
import com.example.and_practice.databinding.ItemFollowingPreviewBinding

class FollowingPreviewViewHolder(
    private val binding: ItemFollowingPreviewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: FollowingPreviewData) {
        if (item.imageUrl.isNotBlank()) {
            binding.followingPreviewIv.load(item.imageUrl) {
                crossfade(true)
            }
        } else {
            binding.followingPreviewIv.setImageDrawable(null)
        }
    }
}
