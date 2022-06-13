package com.qrolic.blueair.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun TitleText(
    text:String,
    color:Color=MaterialTheme.colors.background,
    modifier: Modifier
){
    Text(
        text = text,
        color = color,
        fontSize = 22.sp,
        modifier = modifier,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif,
    )
}

@Composable
fun LightText(
    text:String,
    color:Color=MaterialTheme.colors.background,
    modifier: Modifier
){
    Text(
        text = text,
        color = color,
        fontSize = 16.sp,
        modifier = modifier,
        fontWeight = FontWeight.Light,
        fontFamily = FontFamily.SansSerif,
    )
}

@Composable
fun SmallText(
    text:String,
    color: Color = Color.Unspecified,
    modifier: Modifier=Modifier
){
    Text(
        text = text,
        color = color,
        fontSize = 12.sp,
        modifier = modifier,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.SansSerif,
    )
}

@Composable
fun NormalText(
    text:String,
    color:Color=MaterialTheme.colors.background,
    modifier: Modifier=Modifier,
    fontSize: TextUnit =18.sp,
    textAlign: TextAlign?=null
){
    Text(
        text = text,
        color = color,
        fontSize = fontSize,
        modifier = modifier,
        fontWeight = FontWeight.Normal,
        fontFamily = FontFamily.SansSerif,
        textAlign = textAlign
    )
}



@Composable
fun CapitalText(
    text:String,
    color:Color=MaterialTheme.colors.primary,
    modifier: Modifier=Modifier,
    textAlign: TextAlign?=null
){
    Text(
        text = text.uppercase(),
        color = color,
        fontSize = 16.sp,
        modifier = modifier,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.SansSerif,
        textAlign = textAlign
    )
}