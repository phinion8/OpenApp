package com.priyanshu.openapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.priyanshu.openapp.navigation.SetUpNavigation
import com.priyanshu.openapp.ui.components.BottomNavBar
import com.priyanshu.openapp.ui.components.models.bottomNavItems
import com.priyanshu.openapp.ui.theme.OpenAppTheme
import com.priyanshu.openapp.ui.theme.blue
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var bottomBarVisibility by remember {
                mutableStateOf(false)
            }
            val navController = rememberNavController()
            OpenAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), content = { innerPadding ->

                    SetUpNavigation(
                        navController = navController,
                        innerPaddingValues = innerPadding,
                        showBottomBar = {
                            bottomBarVisibility = it
                        })

                },

                    floatingActionButtonPosition = FabPosition.End,

                    floatingActionButton = {
                        if (bottomBarVisibility) {
                            FloatingActionButton(
                                modifier = Modifier.offset(x = (-16).dp, y = (-16).dp),
                                onClick = {

                                },
                                shape = CircleShape,
                                contentColor = Color.White,
                                containerColor = blue
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "add transaction icon"
                                )
                            }
                        }
                    },

                    bottomBar = {
                        if (bottomBarVisibility) {
                            BottomNavBar(
                                modifier = Modifier.fillMaxWidth(),
                                bottomNavItems = bottomNavItems,
                                navController = navController,
                                onItemClick = {
                                    navController.navigate(it.route) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        this.launchSingleTop = true
                                        this.restoreState = true
                                    }
                                }
                            )
                        }
                    })
            }
        }
    }
}

