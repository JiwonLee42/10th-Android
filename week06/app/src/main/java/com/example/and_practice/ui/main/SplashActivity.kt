package com.example.and_practice.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.and_practice.ui.main.splash.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    if (state.isLoginSuccess) {
                        Log.d("SplashActivity", "로그인 성공")
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java).apply {
                            putExtra("title", "DisCover")
                        })
                        finish()
                    }

                    if (state.errorMessage != null) {
                        Log.e("SplashActivity", "로그인 실패: ${state.errorMessage}")
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java).apply {
                            putExtra("title", "DisCover")
                        })
                        finish()
                    }
                }
            }
        }
    }
}
