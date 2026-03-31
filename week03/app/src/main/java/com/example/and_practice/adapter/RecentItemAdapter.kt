package com.example.and_practice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.and_practice.data.RecentItemData
import com.example.and_practice.databinding.RecentItemBinding

class RecentItemAdapter(
    private var recentProductList: List<RecentItemData>,
    private val onViewClicked: (RecentItemData) -> Unit
) :
    RecyclerView.Adapter<RecentItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentItemViewHolder {
        val binding = RecentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecentItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecentItemViewHolder, position: Int) {
        val item = recentProductList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onViewClicked(item) }
    }

    override fun getItemCount(): Int = recentProductList.size

}
