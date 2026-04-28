package com.example.and_practice.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.and_practice.R
import com.example.and_practice.data.remote.api.ApiClient
import com.example.and_practice.data.remote.api.ApiError
import com.example.and_practice.data.remote.api.toApiError
import com.example.and_practice.data.remote.dto.UpdateMyProfileRequestDTO
import com.example.and_practice.data.remote.dto.handleApiResponse
import com.example.and_practice.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FollowingPreviewAdapter()

        binding.profileImageIv.setImageResource(R.drawable.ic_bg_circle_gray)
        binding.profileFollowingRv.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        binding.profileEditTv.setOnClickListener {
            val editText = EditText(requireContext()).apply {
                hint = "새 닉네임"
                setText(binding.profileNameTv.text)
            }
            AlertDialog.Builder(requireContext())
                .setTitle("프로필 수정")
                .setView(editText)
                .setPositiveButton("저장") { _, _ ->
                    val nickname = editText.text.toString()
                    if (nickname.isBlank()) return@setPositiveButton
                    viewLifecycleOwner.lifecycleScope.launch {
                        handleApiResponse {
                            ApiClient.profileApi.updateProfile(
                                UpdateMyProfileRequestDTO(nickname = nickname)
                            )
                        }
                            .onSuccess {
                                binding.profileNameTv.text = nickname
                                Toast.makeText(requireContext(), "저장됐어요", Toast.LENGTH_SHORT).show()
                            }
                            .onFailure { throwable ->
                                Toast.makeText(requireContext(), throwable.toApiError().defaultMessage, Toast.LENGTH_SHORT).show()
                            }
                    }
                }
                .setNegativeButton("취소", null)
                .show()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            handleApiResponse { ApiClient.profileApi.getMyProfile() }
                .onSuccess { data ->
                    binding.profileNameTv.text = data.nickname

                    val items = data.followings.map {
                        FollowingPreviewData(id = it.userId, imageUrl = it.profileImageUrl)
                    }
                    adapter.submitList(items)
                    binding.profileFollowingTitleTv.text = getString(
                        R.string.profile_following_title_format,
                        items.size
                    )
                }
                .onFailure { throwable ->
                    when (val error = throwable.toApiError()) {
                        is ApiError.Unauthorized -> { /* 로그인 화면으로 이동 */ }
                        else -> Toast.makeText(requireContext(), error.defaultMessage, Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
