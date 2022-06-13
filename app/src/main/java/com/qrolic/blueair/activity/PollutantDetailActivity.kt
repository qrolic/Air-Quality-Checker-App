package com.qrolic.blueair.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qrolic.blueair.activity.ui.theme.BlueAirTheme
import com.qrolic.blueair.components.TopAppBar
import com.qrolic.blueair.model.PollutantModel

class PollutantDetailActivity : ComponentActivity() {

    private lateinit var pollutantModel: PollutantModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        pollutantModel = intent.getParcelableExtra("pollutantModel")!!

        setContent {
            BlueAirTheme {
                PollutantDetail()
            }
        }
    }

    @Composable
    fun PollutantDetail() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            TopAppBar(stringResource(pollutantModel.name)) { onBackPressed() }
            LazyColumn(
                modifier = Modifier.padding(horizontal = 15.dp)
            ) {
                item {
                    DescDetail()
                }
                items(
                    items = pollutantModel.causeList,
                    itemContent = { item: Int ->
                        ItemList(item)
                    }
                )
                item {
                    Text(
                        text = "How does it affect our health?",
                        color = MaterialTheme.colors.onBackground.copy(0.7f),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        text = "Short term effects",
                        color = MaterialTheme.colors.onBackground.copy(0.7f),
                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
                items(
                    items = pollutantModel.sortTermEffectList,
                    itemContent = { item: Int ->
                        ItemList(item)
                    }
                )
                item {
                    Text(
                        text = "Long term effects",
                        color = MaterialTheme.colors.onBackground.copy(0.7f),
                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
                items(
                    items = pollutantModel.longTermEffectList,
                    itemContent = { item: Int ->
                        ItemList(item)
                    }
                )
                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }

    @Composable
    fun ItemList(item: Int) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(
                        CircleShape
                    )
                    .background(MaterialTheme.colors.primaryVariant)
                    .size(10.dp)
                    .align(CenterVertically)
            )
            Text(
                text = stringResource(item),
                color = MaterialTheme.colors.onBackground.copy(0.5f),
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }

    @Composable
    fun DescDetail() {
        Text(
            text = "What is " + stringResource(pollutantModel.name) + "?",
            color = MaterialTheme.colors.onBackground.copy(0.7f),
            fontSize = 16.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(top = 15.dp)
        )
        Text(
            text = stringResource(pollutantModel.desc),
            color = MaterialTheme.colors.onBackground.copy(0.5f),
            fontSize = 14.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(vertical = 20.dp)
        )
        Text(
            text = "Where does it come from?",
            color = MaterialTheme.colors.onBackground.copy(0.7f),
            fontSize = 16.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
        )
    }
}


