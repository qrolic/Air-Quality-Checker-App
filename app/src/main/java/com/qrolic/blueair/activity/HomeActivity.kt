package com.qrolic.blueair.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.qrolic.blueair.activity.bottom_screens.MapView
import com.qrolic.blueair.activity.bottom_screens.MyAir
import com.qrolic.blueair.activity.ui.theme.BlueAirTheme
import com.qrolic.blueair.components.DrawableImage
import com.qrolic.blueair.components.SmallText
import com.qrolic.blueair.model.BottomNavItems
import com.qrolic.blueair.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    val viewModel:HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlueAirTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.primary
                ) {
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(navController = navController)
                        }, content = {
                            NavHostContainer(navController = navController, it)
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun NavHostContainer(
        navController: NavHostController,
        paddingValues: PaddingValues,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.surface)
                .padding(paddingValues)
        ) {
            NavHost(
                navController = navController,
                startDestination = "my_air",
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                builder = {
                    composable("my_air") {
                        MyAir(this@HomeActivity,viewModel,this@HomeActivity)
                    }
                    composable("map") {
                        MapView(viewModel)
                    }
                })
        }
    }

    @Composable
    fun BottomNavigationBar(navController: NavHostController) {

        BottomNavigation(
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.primary
        ) {

            val navBackStackEntry by navController.currentBackStackEntryAsState()

            val currentRoute = navBackStackEntry?.destination?.route

            BottomNavItems.forEach { navItem ->

                BottomNavigationItem(

                    selected = currentRoute == navItem.route,
                    onClick = {
                        navController.navigate(navItem.route) {
                            popUpTo(
                                navController.graph.startDestinationId
                            )
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        DrawableImage(
                            drawable =  navItem.icon,
                            modifier = Modifier
                                .size(20.dp)
                                .padding(bottom = 2.dp),
                            colorFilter = tint(MaterialTheme.colors.onBackground)
                            )
                    },
                    label = {
                        SmallText(
                            text = navItem.label
                        )
                    },
                    alwaysShowLabel = true,
                    unselectedContentColor = MaterialTheme.colors.onBackground.copy(0.8f)
                )
            }
        }
    }
}

