package com.example.and_practice.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.toRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.and_practice.presentation.ui.cart.CartScreen
import com.example.and_practice.presentation.ui.home.HomeScreen
import com.example.and_practice.presentation.ui.mypage.MyPageScreen
import com.example.and_practice.presentation.ui.purchase.PurchaseScreen
import com.example.and_practice.presentation.ui.splash.SplashScreen
import com.example.and_practice.presentation.ui.wish.WishScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Splash,
        modifier = modifier
    ) {
        composable<Splash> {
            SplashScreen(
                onNavigateToHome = {
                    navController.navigate(Home(title = "Discover")) {
                        popUpTo(Splash) { inclusive = true }
                    }
                }
            )
        }
        composable<Home> { backStackEntry ->
            val home = backStackEntry.toRoute<Home>()
            HomeScreen(home.title)
        }
        composable<Cart> {
            CartScreen(
                onNavigateToPurchase = {
                    navController.navigate(Purchase) {
                        popUpTo(Cart) { inclusive = true }
                    }
                }
            )
        }
        composable<Wish> {
            WishScreen()
        }
        composable<MyPage> {
            MyPageScreen()
        }
        composable<Purchase> {
            PurchaseScreen()
        }
    }

}
