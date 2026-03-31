package com.example.and_practice.buy.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.and_practice.adapter.ProductAdapter
import com.example.and_practice.data.BadgeType
import com.example.and_practice.data.ProductData
import com.example.and_practice.databinding.FragmentBuyTab1Binding

class BuyTab1Fragment : Fragment() {

    private var _binding: FragmentBuyTab1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBuyTab1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productList = listOf(
            ProductData(badge = null, name = "Nike Everyday Plus Cushioned", subName = "Training Ankle Socks (6 Pairs)", colorCount = 5, price = 10, productImage = "https://i.imgur.com/SFFENMQ.png"),
            ProductData(badge = null, name = "Nike Elite Crew", subName = "Basketball Socks", colorCount = 7, price = 16, productImage = "https://i.imgur.com/m3PLPzh.png"),
            ProductData(badge = BadgeType.BEST_SELLER, name = "Nike Air Force 1 '07", subName = "Women's Shoes", colorCount = 5, price = 115, productImage = "https://i.imgur.com/5I8jISn.png"),
            ProductData(badge = BadgeType.BEST_SELLER, name = "Jordan Essentials", subName = "Men's Shoes", colorCount = 2, price = 115)
        )

        val adapter = ProductAdapter(productList)

        binding.productListLayout.productRecyclerView.apply {
            layoutManager = GridLayoutManager(
                context,
                2,
            )
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
