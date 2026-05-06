package com.example.and_practice.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.and_practice.data.remote.api.toApiError
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.and_practice.R
import com.example.and_practice.databinding.FragmentProfileBinding
import com.example.and_practice.data.remote.dto.UpdateMyProfileRequestDTO
import com.example.and_practice.domain.repository.ProfileRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    @Inject
    lateinit var repository: ProfileRepository

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
                    val nickname = editText.text.toString().trim()
                    if (nickname.isBlank()) return@setPositiveButton
                    updateNickname(nickname)
                }
                .setNegativeButton("취소", null)
                .show()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            loadProfile(adapter)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private suspend fun loadProfile(adapter: FollowingPreviewAdapter) {
        runCatching { repository.getMyProfile() }
            .onSuccess { profile ->
                val followings = profile.followings.map {
                    FollowingPreviewData(
                        id = it.userId,
                        imageUrl = it.profileImageUrl
                    )
                }

                binding.profileNameTv.text = profile.nickname
                adapter.submitList(followings)
                binding.profileFollowingTitleTv.text = getString(
                    R.string.profile_following_title_format,
                    followings.size
                )
            }
            .onFailure { throwable ->
                Toast.makeText(
                    requireContext(),
                    throwable.toApiError().defaultMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun updateNickname(nickname: String) {
        viewLifecycleOwner.lifecycleScope.launch {
            runCatching {
                repository.updateMyProfile(UpdateMyProfileRequestDTO(nickname = nickname))
            }
                .onSuccess {
                    binding.profileNameTv.text = nickname
                    Toast.makeText(requireContext(), "저장됐어요", Toast.LENGTH_SHORT).show()
                }
                .onFailure { throwable ->
                    Toast.makeText(
                        requireContext(),
                        throwable.toApiError().defaultMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }
}
