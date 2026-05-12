package com.example.and_practice.presentation.navigation

import androidx.annotation.DrawableRes
import com.example.and_practice.R
import kotlin.reflect.KClass

sealed class BottomNavItem(
    val label: String,
    @DrawableRes val iconRes: Int,
    val route: KClass<*>,
    val destination: Any
) {
    data object HomeItem : BottomNavItem(
        label = "홈",
        iconRes = R.drawable.ic_housesimple,
        route = Home::class,
        destination = Home(title = "Discover")
    )

    data object WishItem : BottomNavItem(
        label = "위시리스트",
        iconRes = R.drawable.ic_heartstraight,
        route = Wish::class,
        destination = Wish
    )

    data object CartItem : BottomNavItem(
        label = "장바구니",
        iconRes = R.drawable.ic_bagsimple,
        route = Cart::class,
        destination = Cart
    )

    data object PurchaseItem : BottomNavItem(
        label = "구매하기",
        iconRes = R.drawable.ic_listmagnifyingglass,
        route = Purchase::class,
        destination = Purchase
    )

    data object MyPageItem : BottomNavItem(
        label = "프로필",
        iconRes = R.drawable.ic_user,
        route = MyPage::class,
        destination = MyPage
    )
}
