package com.example.and_practice.presentation.buy

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.and_practice.adapter.ProductAdapter
import com.example.and_practice.data.BadgeType
import com.example.and_practice.data.ProductData
import com.example.and_practice.databinding.FragmentProductListBinding
class ProductListFragment: Fragment() {
    private lateinit var binding: FragmentProductListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productList = mutableListOf(
            ProductData(null, "Nike Everyday Plus Cushioned", "Training Ankle Socks (6 Pairs)", 5,10,"https://i.imgur.com/SFFENMQ.png"),
            ProductData(null, "Nike Elite Crew", "Basketball Socks", 7,16,"https://i.imgur.com/m3PLPzh.png"),
            ProductData(BadgeType.BEST_SELLER, "Nike Air Force 1 '07", "Women's Shoes", 5,115,"https://i.imgur.com/5I8jISn.png"),
            ProductData(BadgeType.BEST_SELLER, "Jordan ENike Air Force 1 '07ssentials", "Men's Shoes", 2,115,),

        )

        val adapter = ProductAdapter(productList) { item ->
            // 구매하기, 제품 상세 페이지로 이동
        }

        binding.productRecyclerView.apply {
            layoutManager = GridLayoutManager(
                context,
                2,
            )
            this.adapter = adapter
        }


    }
}