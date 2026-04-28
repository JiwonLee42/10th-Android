package com.example.and_practice.presentation.wish

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.and_practice.data.database.ProductDatabase
import com.example.and_practice.databinding.FragmentWishBinding
import com.example.and_practice.presentation.buy.BadgeType
import com.example.and_practice.presentation.buy.ProductData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WishFragment : Fragment() {
    private var _binding: FragmentWishBinding? = null
    private val binding get() = _binding!!

    private lateinit var productDao: com.example.and_practice.data.dao.ProductDao

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


        val adapter = WishAdapter()

        binding.productRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            this.adapter = adapter
        }

        val db = ProductDatabase.getInstance(requireContext())
        productDao = db.productDao()

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val wishList = productDao.getWishlist().map {
                ProductData(
                    id = it.id,
                    badge = if (it.badgeType == BadgeType.BEST_SELLER.name) {
                        BadgeType.BEST_SELLER
                    } else {
                        null
                    },
                    name = it.name,
                    subName = it.content,
                    colorCount = it.color,
                    price = it.price,
                    productImage = it.imageUrl,
                    isLiked = it.isLiked
                )
            }

            withContext(Dispatchers.Main) {
                adapter.submitList(wishList)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
