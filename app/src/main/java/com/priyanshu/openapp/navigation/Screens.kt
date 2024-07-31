package com.priyanshu.openapp.navigation

sealed class Screens(val route: String) {
    data object Splash : Screens(route = Routes.SPLASH_SCREEN)
    data object OnBoarding : Screens(route = Routes.ON_BOARDING_SCREEN)
    data object Links : Screens(route = Routes.LINKS_SCREEN)
    data object Campaigns : Screens(route = Routes.CAMPAIGNS_SCREEN)
    data object Courses: Screens(route = Routes.COURSES_SCREEN)
    data object Profile: Screens(route = Routes.PROFILE_SCREEN)
}