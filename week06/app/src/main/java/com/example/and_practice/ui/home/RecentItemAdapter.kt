package com.example.and_practice.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.and_practice.databinding.RecentItemBinding

class RecentItemAdapter(
    private val onViewClicked: (RecentItemData) -> Unit
) :
    ListAdapter<RecentItemData, RecentItemViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentItemViewHolder {
        val binding = RecentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecentItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener { onViewClicked(item) }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<RecentItemData>() {
            override fun areItemsTheSame(
                oldItem: RecentItemData,
                newItem: RecentItemData
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: RecentItemData,
                newItem: RecentItemData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
