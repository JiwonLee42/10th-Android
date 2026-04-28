package com.example.and_practice

import android.app.Application
import com.example.and_practice.data.remote.TokenStorage

class AndPracticeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 토큰 초기화
        TokenStorage.init(this)
    }
}
