package com.example.and_practice.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.and_practice.presentation.component.AppBottomBar
import com.example.and_practice.presentation.navigation.AppNavHost
import com.example.and_practice.presentation.navigation.BottomNavItem

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    // backstack top에 보이는 화면을 state로 가져옴
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            AppBottomBar(
                currentRoute = currentRoute,
                items = bottomNavItems,
                onItemClick = { item ->
                    navController.navigateToBottomTab(item.destination)
                }
            )
        }
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

private val bottomNavItems = listOf(
    BottomNavItem.HomeItem,
    BottomNavItem.WishItem,
    BottomNavItem.CartItem,
    BottomNavItem.PurchaseItem,
    BottomNavItem.MyPageItem
)

// 선택된 destination으로 이동
private fun NavHostController.navigateToBottomTab(destination: Any) {
    navigate(destination) {
        launchSingleTop = true
    }
}
