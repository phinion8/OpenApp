package com.priyanshu.openapp.ui.screens.splash.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.priyanshu.openapp.R
import com.priyanshu.openapp.navigation.Screens
import com.priyanshu.openapp.ui.components.ShowLottieAnimation
import com.priyanshu.openapp.ui.theme.lightGray
import com.priyanshu.openapp.ui.components.CustomElevatedButton
import com.priyanshu.openapp.ui.screens.splash.onboarding.viewModel.OnBoardingViewModel

@Composable
fun OnBoardingScreen(
    navController: NavController,
    viewModel: OnBoardingViewModel = hiltViewModel(),
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        ShowLottieAnimation(rawRes = R.raw.onboarding_anim, modifier = Modifier.size(380.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Got an app link to share?",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Open any app link within the app itself with a single click, instead of in the browser.",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = lightGray
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(42.dp))

            CustomElevatedButton(onClick = {
                viewModel.saveOnBoardingState(completed = true)
                navController.popBackStack()
                navController.navigate(Screens.Links.route)
            }, text = "Get Started")
        }

    }

}