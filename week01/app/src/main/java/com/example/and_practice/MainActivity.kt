package com.example.and_practice

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.and_practice.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 행복
        binding.happyImage.setOnClickListener {
            handleClick(it)
        }

        // 신남/흥분
        binding.excitedImage.setOnClickListener {
            handleClick(it)
        }

        // 불안
        binding.anxiousImage.setOnClickListener {
            handleClick(it)
        }

        // 화남
        binding.angryImage.setOnClickListener {
            handleClick(it)
        }

        // 평범
        binding.normalImage.setOnClickListener {
            handleClick(it)
        }

        observeSelectedEmotion()
    }

    // 이미지 클릭에 따라서 감정 분기 처리
    private fun handleClick(view: View) {
        when(view.id){
            R.id.happyImage -> {
                viewModel.selectedEmotion(Emotion.HAPPY)
            }
            R.id.excitedImage -> {
                viewModel.selectedEmotion(Emotion.EXCITED)
            }
            R.id.normalImage -> {
                viewModel.selectedEmotion(Emotion.NORMAL)
            }
            R.id.anxiousImage -> {
                viewModel.selectedEmotion(Emotion.ANXIOUS)
            }
            R.id.angryImage -> {
                viewModel.selectedEmotion(Emotion.ANGRY)
            }
        }
    }

    // 감정에 따라서 색상 변경하기
    private fun updateSelectedText(emotion: Emotion) {
        val black = getColor(android.R.color.black)

        binding.happyText.setTextColor(if (emotion == Emotion.HAPPY) getColor(R.color.emotion_happy) else black)
        binding.excitedText.setTextColor(if (emotion == Emotion.EXCITED) getColor(R.color.emotion_excited) else black)
        binding.normalText.setTextColor(if (emotion == Emotion.NORMAL) getColor(R.color.emotion_normal) else black)
        binding.anxiousText.setTextColor(if (emotion == Emotion.ANXIOUS) getColor(R.color.emotion_anxious) else black)
        binding.angryText.setTextColor(if (emotion == Emotion.ANGRY) getColor(R.color.emotion_angry) else black)
    }

    // 뷰모델 상태 구독
    private fun observeSelectedEmotion() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.selectedEmotion.collect { emotion ->
                    emotion?.let { updateSelectedText(it) } ?: clearSelectedText()
                }
            }
        }
    }

    // null일때 false로 초기화하기
    private fun clearSelectedText() {
        binding.happyText.isSelected = false
        binding.excitedText.isSelected = false
        binding.normalText.isSelected = false
        binding.anxiousText.isSelected = false
        binding.angryText.isSelected = false
    }
}
