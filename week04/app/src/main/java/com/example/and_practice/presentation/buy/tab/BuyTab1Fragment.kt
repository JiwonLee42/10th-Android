package com.example.and_practice.buy.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.and_practice.data.database.ProductDatabase
import com.example.and_practice.data.entity.CategoryEntity
import com.example.and_practice.data.entity.ProductEntity
import com.example.and_practice.databinding.FragmentBuyTab1Binding
import com.example.and_practice.presentation.buy.BadgeType
import com.example.and_practice.presentation.buy.ProductAdapter
import com.example.and_practice.presentation.buy.ProductData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BuyTab1Fragment : Fragment() {

    private var _binding: FragmentBuyTab1Binding? = null
    private val binding get() = _binding!!
    private lateinit var productAdapter: ProductAdapter
    private lateinit var productDao: com.example.and_practice.data.dao.ProductDao
    private lateinit var categoryDao: com.example.and_practice.data.dao.CategoryDao

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

        val db = ProductDatabase.getInstance(requireContext())
        productDao = db.productDao()
        categoryDao = db.categoryDao()

        productAdapter = ProductAdapter(
            onItemClick = { product -> },
            onHeartClick = { product ->
                toggleProductLike(product)
            }
        )

        binding.productRecyclerView.apply {
            layoutManager = GridLayoutManager(
                context,
                2,
            )
            adapter = productAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            categoryDao.insertCategory(CategoryEntity(name = "Shoes"))
            categoryDao.insertCategory(CategoryEntity(name = "Socks"))
            categoryDao.insertCategory(CategoryEntity(name = "T-Shirts"))

            val categoriesByName = categoryDao.getAllCategories().associateBy { it.name }

            productDao.insertProduct(
                ProductEntity(
                    name = "Nike Everyday Plus Cushioned",
                    content = "Training Ankle Socks (6 Pairs)",
                    color = 5,
                    imageUrl = "https://i.imgur.com/SFFENMQ.png",
                    price = 10,
                    isLiked = false,
                    categoryId = categoriesByName["Socks"]?.id ?: 0,
                    badgeType = "NONE"
                )
            )
            productDao.insertProduct(
                ProductEntity(
                    name = "Nike Elite Crew",
                    content = "Basketball Socks",
                    color = 7,
                    imageUrl = "https://i.imgur.com/m3PLPzh.png",
                    price = 16,
                    isLiked = true,
                    categoryId = categoriesByName["Socks"]?.id ?: 0,
                    badgeType = "NONE"
                )
            )
            productDao.insertProduct(
                ProductEntity(
                    name = "Nike Air Force 1 '07",
                    content = "Women's Shoes",
                    color = 5,
                    imageUrl = "https://i.imgur.com/5I8jISn.png",
                    price = 115,
                    isLiked = false,
                    categoryId = categoriesByName["Shoes"]?.id ?: 0,
                    badgeType = BadgeType.BEST_SELLER.name
                )
            )
            productDao.insertProduct(
                ProductEntity(
                    name = "Jordan Essentials",
                    content = "Men's Shoes",
                    color = 2,
                    imageUrl = "",
                    price = 115,
                    isLiked = false,
                    categoryId = categoriesByName["Shoes"]?.id ?: 0,
                    badgeType = BadgeType.BEST_SELLER.name
                )
            )

            val productList = productDao.getAll().map {
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
                productAdapter.submitList(productList)
            }
        }
    }

    // 상품 좋아요
    private fun toggleProductLike(product: ProductData) {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            val targetProduct = productDao.getById(product.id) ?: return@launch
            val updatedIsLiked = !targetProduct.isLiked

            // isLiked가 true된 상태로 타켓 프로덕트를 업데이트
            productDao.updateProduct(
                targetProduct.copy(isLiked = updatedIsLiked)
            )

            val updatedProductList = productAdapter.currentList.map {
                if (it.id == product.id) {
                    it.copy(isLiked = updatedIsLiked)
                } else {
                    it
                }
            }

            withContext(Dispatchers.Main) {
                productAdapter.submitList(updatedProductList)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
