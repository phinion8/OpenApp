package com.priyanshu.openapp.ui.components.models

import androidx.annotation.DrawableRes
import com.priyanshu.openapp.navigation.Screens
import com.priyanshu.openapp.R

data class BottomNavItem(
    @DrawableRes
    val icon: Int,
    val title: String,
    val route: String
)

val bottomNavItems = listOf<BottomNavItem>(
    BottomNavItem(
        icon = R.drawable.ic_link,
        title = "Links",
        route = Screens.Links.route
    ),
    BottomNavItem(
        icon = R.drawable.ic_book,
        title = "Courses",
        route = Screens.Courses.route
    ),
    BottomNavItem(
        icon = R.drawable.ic_megaphone,
        title = "Campaigns",
        route = Screens.Campaigns.route
    ),
    BottomNavItem(
        icon = R.drawable.ic_profile,
        title = "Profile",
        route = Screens.Profile.route
    )

)