package com.example.and_practice.wish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.and_practice.adapter.WishAdapter
import com.example.and_practice.data.ProductData
import com.example.and_practice.databinding.FragmentWishBinding

class WishFragment : Fragment() {
    private var _binding: FragmentWishBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWishBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productList = listOf(
            ProductData(badge = null, name = "Nike Everyday Plus Cushioned", subName = "Training Ankle Socks (6 Pairs)", colorCount = 5, price = 10, productImage = "https://i.imgur.com/SFFENMQ.png"),
            ProductData(null, "Nike Air Force 1 '07", "Women's Shoes", 5,115,"https://i.imgur.com/5I8jISn.png"),
        )

        val adapter = WishAdapter(productList)

        binding.productListLayout.productRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            this.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
