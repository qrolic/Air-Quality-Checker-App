package com.qrolic.blueair.utils

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.qrolic.blueair.R
import com.qrolic.blueair.components.WhiteButton

@Composable
fun ServerError(
    isDisplayed: Boolean
) {
    if (isDisplayed) {
        val context = LocalContext.current
        AlertDialog(
            onDismissRequest = {
            },
            title = {


            },
            confirmButton = {
                WhiteButton(text = stringResource(R.string.okay)) {
                    (context as Activity).finish()
                }
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
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
                        text = stringResource(R.string.server_error),
                        color = MaterialTheme.colors.onBackground,
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        text = stringResource(R.string.something_went_wrong_please_try_again_later),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onBackground,
                        fontFamily = FontFamily.SansSerif,

                        )
                }
            },
        )
    }
}