package com.qrolic.blueair.model

import com.qrolic.blueair.R

data class BottomNavItem(
    val label:String,
    val icon:Int,
    val route:String
)

val BottomNavItems = listOf(
    BottomNavItem(
        label = "My Air",
        icon = R.drawable.myair,
        route = "my_air"
    ),
    BottomNavItem(
        label = "Map",
        icon = R.drawable.map,
        route = "map"
    ),
)

