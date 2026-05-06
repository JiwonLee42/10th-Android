package com.example.and_practice.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.and_practice.R
import com.example.and_practice.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()

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
                    viewModel.updateNickname(nickname)
                }
                .setNegativeButton("취소", null)
                .show()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.uiState.collect { state ->
                        binding.profileNameTv.text = state.nickname
                        adapter.submitList(state.followings)
                        binding.profileFollowingTitleTv.text = getString(
                            R.string.profile_following_title_format,
                            state.followings.size
                        )
                    }
                }

                launch {
                    viewModel.eventState.collect { event ->
                        when (event) {
                            is ProfileEventState.ShowError -> {
                                Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
                            }

                            ProfileEventState.ProfileUpdated -> {
                                Toast.makeText(requireContext(), "저장됐어요", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
