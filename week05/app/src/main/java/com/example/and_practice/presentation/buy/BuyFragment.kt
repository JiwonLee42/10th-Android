package com.example.and_practice.presentation.buy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.and_practice.R
import com.example.and_practice.databinding.FragmentBuyBinding
import com.example.and_practice.presentation.buy.tab.BuyTab1Fragment
import com.example.and_practice.presentation.buy.tab.BuyTab2Fragment
import com.example.and_practice.presentation.buy.tab.BuyTab3Fragment
import com.google.android.material.tabs.TabLayout

class BuyFragment : Fragment() {
    private var _binding: FragmentBuyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBuyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabLayout = binding.buyTabLayout
        if (savedInstanceState == null){
            childFragmentManager.beginTransaction()
                .replace(R.id.tab_container, BuyTab1Fragment())
                .commit()
        }
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0 -> {
                        childFragmentManager.beginTransaction()
                            .replace(R.id.tab_container, BuyTab1Fragment())
                            .commit()
                    }
                    1 -> {
                        childFragmentManager.beginTransaction()
                            .replace(R.id.tab_container, BuyTab2Fragment())
                            .commit()
                    }
                    2 -> {
                        childFragmentManager.beginTransaction()
                            .replace(R.id.tab_container, BuyTab3Fragment())
                            .commit()
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
