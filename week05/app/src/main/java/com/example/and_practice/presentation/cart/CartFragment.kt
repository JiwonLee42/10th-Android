package com.example.and_practice.presentation.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.and_practice.R
import com.example.and_practice.databinding.FragmentCartBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cartOrderBtn.setOnClickListener {
            // 현재 Fragment가 붙어있는 Activity를 가져와서, Bnv를 찾고 선택 상태로 변경함
            requireActivity()
                .findViewById<BottomNavigationView>(R.id.main_bnv)
                .selectedItemId = R.id.buyFragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
