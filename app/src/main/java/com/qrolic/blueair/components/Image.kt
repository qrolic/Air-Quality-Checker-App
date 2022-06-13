package com.qrolic.blueair.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource

@Composable
fun DrawableImage(
    modifier: Modifier=Modifier,
    drawable:Int,
    colorFilter: ColorFilter? = null
){
    Image(
        painter = painterResource(drawable),
        contentDescription = "",
        modifier = modifier,
        colorFilter = colorFilter
    )
}