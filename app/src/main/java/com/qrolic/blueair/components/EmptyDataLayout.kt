package com.qrolic.blueair.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qrolic.blueair.R

@Composable
fun EmptyDataLayout(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.no_data),
            contentDescription = "",
            modifier = Modifier.size(120.dp).padding(15.dp),
            tint = MaterialTheme.colors.onBackground.copy(0.3f)
        )
        Text(
            text = stringResource(R.string.no_data_available),
            color = MaterialTheme.colors.onBackground.copy(0.6f),
            fontSize = 22.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(top = 15.dp)
        )
    }

}