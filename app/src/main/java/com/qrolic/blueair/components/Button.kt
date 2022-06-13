package com.qrolic.blueair.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun WhiteButton(
    modifier: Modifier = Modifier,
    text:String,
    textModifier: Modifier=Modifier,
    onClick:()->Unit,

    ){
    androidx.compose.material.Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.primary
        )
    ) {
        CapitalText(
            text = text,
            modifier = textModifier.fillMaxWidth().align(CenterVertically),
            textAlign = TextAlign.Center
        )
    }
}