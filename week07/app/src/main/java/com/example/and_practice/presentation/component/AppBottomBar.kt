package com.example.and_practice.presentation.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import com.example.and_practice.presentation.navigation.BottomNavItem

@Composable
fun AppBottomBar(
    currentRoute: NavDestination?,
    items: List<BottomNavItem>,
    onItemClick: (BottomNavItem) -> Unit
) {
    NavigationBar {
        items.forEach { item ->
            val isSelected = currentRoute?.hasRoute(item.route) == true

            NavigationBarItem(
                selected = isSelected,
                onClick = { onItemClick(item) },
                // 기본 색상, 선택 시 색상 설정
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF000000),
                    selectedTextColor = Color(0xFF000000),
                    unselectedIconColor = Color(0xFF767676),
                    unselectedTextColor = Color(0xFF767676),
                    indicatorColor = Color.Transparent
                ),
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(text = item.label)
                }
            )
        }
    }
}
