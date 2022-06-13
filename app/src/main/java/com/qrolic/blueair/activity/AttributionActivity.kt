package com.qrolic.blueair.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qrolic.blueair.R
import com.qrolic.blueair.activity.ui.theme.BlueAirTheme
import com.qrolic.blueair.components.DefaultLinkifyText
import com.qrolic.blueair.components.TopAppBar
import com.qrolic.blueair.model.home.AttributionModel

class AttributionActivity : ComponentActivity() {

    private lateinit var attributionList :ArrayList<AttributionModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        attributionList = intent.getParcelableArrayListExtra("attributionModel")!!
        setContent {
            BlueAirTheme {
                AttributionForm()
            }
        }
    }

    @Composable
    fun AttributionForm() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            TopAppBar("Attribution") { onBackPressed() }
            LazyColumn(
                modifier = Modifier.padding(horizontal = 15.dp)
            ) {
                items(
                    items = attributionList,
                    itemContent = { item: AttributionModel ->
                        AttributionItem(item)
                    }
                )
            }
        }
    }

    @Composable
    fun AttributionItem(item: AttributionModel) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.appicon),
                contentDescription ="",
                modifier = Modifier
                    .size(30.dp)
                    .align(CenterVertically),
                tint = MaterialTheme.colors.onBackground
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp)
            ) {
                Text(
                    text = item.name.toString(),
                    color = MaterialTheme.colors.onBackground.copy(0.7f),
                    fontSize = 16.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 10.dp)
                )

//                Text(
//                    text = item.url.toString(),
//                    color = MaterialTheme.colors.primary,
//                    fontSize = 16.sp,
//                    fontFamily = FontFamily.SansSerif,
//                    fontWeight = FontWeight.Medium,
//                    modifier = Modifier.padding(top = 10.dp)
//                )
                DefaultLinkifyText(text = item.url.toString())
            }
        }
    }
}

