package com.example.and_practice

import android.app.Application
import com.example.and_practice.data.remote.TokenStorage
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 토큰 초기화
        TokenStorage.init(this)
    }
}
