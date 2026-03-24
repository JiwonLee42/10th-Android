package com.example.and_practice.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.and_practice.databinding.FragmentHomeBinding

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
