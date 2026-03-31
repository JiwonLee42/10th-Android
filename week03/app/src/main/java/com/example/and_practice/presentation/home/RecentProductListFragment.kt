package com.example.and_practice.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.and_practice.adapter.RecentItemAdapter
import com.example.and_practice.data.RecentItemData
import com.example.and_practice.databinding.FragmentRecentProductListBinding

class RecentProductListFragment : Fragment() {

    private lateinit var binding: FragmentRecentProductListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productList = mutableListOf(
            RecentItemData("Air Jordan XXXVI", "US$185", "https://ibb.co/5xBcLfd3"),
            RecentItemData("Nike Air Force 1 '07", "US$115", "https://ibb.co/PvGsm84K"),
        )

        val adapter = RecentItemAdapter(productList) { item ->
            // 구매하기, 제품 상세 페이지로 이동
        }

        binding.recentRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            this.adapter = adapter
        }


    }

}