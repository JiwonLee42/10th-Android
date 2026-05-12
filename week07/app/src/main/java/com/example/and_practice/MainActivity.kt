package com.example.and_practice

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.and_practice.base.BaseActivity
import com.example.and_practice.presentation.ui.MainScreen
import com.example.and_practice.presentation.ui.theme.AndPracticeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndPracticeTheme {
                MainScreen()
            }
        }
    }
}
