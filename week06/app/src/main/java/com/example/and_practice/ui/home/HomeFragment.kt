package com.example.and_practice.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.and_practice.data.local.database.ProductDatabase
import com.example.and_practice.data.model.CategoryEntity
import com.example.and_practice.data.model.ProductEntity
import com.example.and_practice.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private var lastBackPressed = 0L
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val title = requireActivity().intent.getStringExtra("title") ?: "Discover"
        binding.title.text = title
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RecentItemAdapter { _ ->
            // TODO: 제품 상세 화면 이동
        }



        binding.recentRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            this.adapter = adapter
        }


        // DB 인스턴스 가져오기
        val db = ProductDatabase.getInstance(requireContext())
        val productDao = db.productDao()
        val categoryDao = db.categoryDao()

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            categoryDao.insertCategory(CategoryEntity(name = "Shoes"))
            categoryDao.insertCategory(CategoryEntity(name = "Socks"))
            categoryDao.insertCategory(CategoryEntity(name = "T-Shirts"))

            productDao.insertProduct(
                ProductEntity(
                    name = "Air Jordan",
                    content = "There is only one way to celebrate....",
                    color = 5,
                    imageUrl = "https://i.imgur.com/fiGYVQa.png",
                    price = 239,
                    isLiked = false,
                    categoryId = 1
                )
            )
            productDao.insertProduct(
                ProductEntity(
                    name = "Nike Air Force 1 '07'",
                    content = "There is only one way to celebrate....",
                    color = 5,
                    imageUrl = "https://i.imgur.com/z4EKdUx.png",
                    price = 115,
                    isLiked = false,
                    categoryId = 1
                )
            )

            val recentItems = productDao.getAll().map {
                RecentItemData(
                    name = it.name,
                    price = "US$${it.price}",
                    productImage = it.imageUrl
                )
            }


            withContext(Dispatchers.Main) {
                adapter.submitList(recentItems)
                Log.d("DB", "모든 상품: $recentItems")
            }
        }

        // 생성한 콜백을 등록 및 해제
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            onBackPressedCallback
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val onBackPressedCallback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // 마지막으로 눌렀을때와 지금 눌렀을때의 시간 차가 2초 미만이라면 실행
            if (System.currentTimeMillis() - lastBackPressed < 2000) {
                // 2초 내에 home에서 두번 눌렀다면 액티비티 종료
                requireActivity().finish()
            } else {
                // 한번 누르면, 한번 더 누르면 종료라는
                // 한번 눌렀을 때, 누른 시간을 저장
                Toast.makeText(context, "앱을 종료하려면 뒤로 가기로 한 번 더 눌러주세요.", Toast.LENGTH_SHORT).show()
                lastBackPressed = System.currentTimeMillis()
            }
        }
    }
}
