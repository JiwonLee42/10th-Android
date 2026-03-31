package com.example.and_practice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.and_practice.data.ProductData
import com.example.and_practice.databinding.WishItemBinding

class WishAdapter(
    private var productList: List<ProductData>,
    private val onItemClick: (ProductData) -> Unit = {}
) : RecyclerView.Adapter<WishItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishItemViewHolder {
        val binding = WishItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return WishItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WishItemViewHolder, position: Int) {
        val item = productList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener { onItemClick(item) }
    }

    override fun getItemCount(): Int = productList.size

    fun submitList(items: List<ProductData>) {
        productList = items
        notifyDataSetChanged()
    }
}
