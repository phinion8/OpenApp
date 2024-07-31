package com.priyanshu.openapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.priyanshu.openapp.ui.screens.splash.SplashScreen
import com.priyanshu.openapp.ui.screens.links.LinksScreen
import com.priyanshu.openapp.ui.screens.onboarding.OnBoardingScreen
import kotlin.math.truncate

@Composable
fun SetUpNavigation(
    navController: NavHostController,
    innerPaddingValues: PaddingValues,
    showBottomBar: (Boolean) -> Unit
) {
    
    NavHost(navController = navController, startDestination = Screens.Splash.route) {

        composable(route = Screens.Splash.route){

            showBottomBar(false)
            SplashScreen(navController = navController)

        }

        composable(route = Screens.OnBoarding.route){

            showBottomBar(false)
            OnBoardingScreen(navController = navController)

        }

        composable(route = Screens.Links.route){

            showBottomBar(true)
            LinksScreen(innerPaddingValues)
        }

        composable(route = Screens.Courses.route){
            showBottomBar(true)
        }

        composable(route = Screens.Campaigns.route){
            showBottomBar(true)
        }

        composable(route = Screens.Profile.route){
            showBottomBar(true)
        }

    }
    
    
    
}