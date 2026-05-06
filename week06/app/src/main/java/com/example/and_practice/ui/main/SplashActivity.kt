package com.example.and_practice.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.and_practice.ui.main.splash.SplashEventState
import com.example.and_practice.ui.main.splash.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.eventState.collect { event ->
                        when (event) {
                            SplashEventState.NavigateToMain -> {
                                startActivity(
                                    Intent(this@SplashActivity, MainActivity::class.java).apply {
                                        putExtra("title", "DisCover")
                                    }
                                )
                                finish()
                            }

                            is SplashEventState.ShowError -> {
                                Toast.makeText(
                                    this@SplashActivity,
                                    event.message,
                                    Toast.LENGTH_SHORT
                                ).show()

                                startActivity(
                                    Intent(this@SplashActivity, MainActivity::class.java).apply {
                                        putExtra("title", "DisCover")
                                    }
                                )
                                finish()
                            }
                        }
                    }
                }
            }
        }
    }
}
