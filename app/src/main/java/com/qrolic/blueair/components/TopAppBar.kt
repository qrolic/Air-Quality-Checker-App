package com.qrolic.blueair.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.qrolic.blueair.R

@Composable
fun TopAppBar(
    text:String,
    onClick:()->Unit
) {
    androidx.compose.material.TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 3.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onClick) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "",
                    tint = MaterialTheme.colors.onBackground,
                    modifier = Modifier.size(30.dp)
                )
            }
            NormalText(
                text = text,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.padding(start = 15.dp)
            )
        }
    }
}