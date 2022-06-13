package com.qrolic.blueair.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.qrolic.blueair.R
import com.qrolic.blueair.components.WhiteButton


@Composable
fun NetworkError(
    isDisplayed: Boolean,
    onClick:()->Unit
) {
    if (isDisplayed) {
        AlertDialog(
            modifier = Modifier.fillMaxWidth(),
            onDismissRequest = {
            },
            title = {


            },
            confirmButton = {
                Box(modifier = Modifier.fillMaxWidth()){
                    WhiteButton(
                        text = stringResource(R.string.retry),
                        modifier = Modifier.fillMaxWidth(),
                        onClick = onClick
                    )
                }
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.error),
                        contentDescription = "",
                        modifier = Modifier
                            .size(50.dp)
                            .padding(top = 15.dp),
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
                    )
                    Text(
                        text = stringResource(R.string.internet_error),
                        color = MaterialTheme.colors.onBackground,
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        text = stringResource(R.string.please_check_your_internet_connection),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onBackground,
                        fontFamily = FontFamily.SansSerif,
                    )
                }
            },
        )
    }
}