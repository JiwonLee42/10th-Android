package com.example.and_practice.presentation.ui.home

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.and_practice.R
import com.example.and_practice.presentation.ui.theme.AndPracticeTheme

// 현재 시간 가져오기
private val homeDateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MM월 dd일")
private val homeDateTextStyle = TextStyle(
    color = Color(0xFF767676),
    fontSize = 16.sp,
    fontWeight = FontWeight.Normal,
    letterSpacing = (-0.4).sp
)

@Composable
fun HomeScreen(title: String) {
    val now = LocalDateTime.now()
    val dayOfWeek = now.dayOfWeek.getDisplayName(java.time.format.TextStyle.FULL, Locale.KOREAN)
    val context = LocalContext.current
    // 기본 0으로 초기화
    var backPressedTime by remember { mutableLongStateOf(0L) }

    // 뒤로가기 버튼이 눌리면 실행되는 함수
    BackHandler {
        val currentTime = System.currentTimeMillis()

        // 2초 내에 한번 더 누르면 종료
        if (currentTime - backPressedTime < 2000) {
            // activity로 타입변경 후 종료
            (context as? Activity)?.finish()
        } else {
            // 한번 눌렸으면,현재 시간을 가져옴
            backPressedTime = currentTime
            Toast.makeText(context, "한 번 더 누르면 종료됩니다", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding()
            .padding(horizontal = 17.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 50.dp, bottom = 50.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "${now.format(homeDateFormatter)} $dayOfWeek",
                style = homeDateTextStyle
            )
        }

        Image(
            painter = painterResource(id = R.drawable.home_logo),
            contentDescription = "홈 이미지",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(375f / 500f),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(500.dp))
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "id:pixel_7"
)
@Composable
fun HomeScreenPreview() {
    AndPracticeTheme {
        HomeScreen("Discover")
    }
}
