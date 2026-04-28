package com.example.and_practice.presentation.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.and_practice.data.remote.TokenStorage
import com.example.and_practice.data.repository.AuthRepository
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            runCatching { AuthRepository().login() }
                .onSuccess { Log.d("SplashActivity", "로그인 성공, 토큰: ${TokenStorage.getAccessToken()}") }
                .onFailure { Log.e("SplashActivity", "로그인 실패", it) }

            startActivity(Intent(this@SplashActivity, MainActivity::class.java).apply {
                putExtra("title", "DisCover")
            })
            finish()
        }
    }
}
