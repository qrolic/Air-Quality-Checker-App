package com.qrolic.blueair.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qrolic.blueair.R
import com.qrolic.blueair.activity.ui.theme.*

class AirGuideActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlueAirTheme {
                AirGuide()
            }
        }
    }

    @Composable
    fun AirGuide() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            com.qrolic.blueair.components.TopAppBar("AQI Guide"){onBackPressed()}
            LazyColumn(
                modifier = Modifier.padding(horizontal = 15.dp)
            ) {
                item {
                    GuideDetail()
                }
            }
        }
    }

    @Composable
    fun GuideDetail() {
        Text(
            text = stringResource(id = R.string.air_guide_desc),
            color = MaterialTheme.colors.onBackground.copy(0.5f),
            fontSize = 14.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(bottom = 10.dp, top = 15.dp)
        )
        AQILevel(
            level = stringResource(id = R.string.aqi_level_good),
            levelDesc = stringResource(id = R.string.aqi_level_good_desc),
            backgroundColor = Green,
            icon=R.drawable.good
        )
        AQILevel(
            level = stringResource(id = R.string.aqi_level_moderate),
            levelDesc = stringResource(id = R.string.aqi_level_moderate_desc),
            backgroundColor = Yellow,
            icon=R.drawable.moderate
        )
        AQILevel(
            level = stringResource(id = R.string.aqi_level_unhealthy_for_sensitive_group),
            levelDesc = stringResource(id = R.string.aqi_level_unhealthy_for_sensitive_group_desc),
            backgroundColor = Orange,
            icon=R.drawable.unhealthy
        )
        AQILevel(
            level = stringResource(id = R.string.aqi_leve_unhealthy),
            levelDesc = stringResource(id = R.string.aqi_leve_unhealthy_desc),
            backgroundColor = Red,
            icon=R.drawable.mask2
        )
        AQILevel(
            level = stringResource(id = R.string.aqi_leve_very_unhealthy),
            levelDesc = stringResource(id = R.string.aqi_leve_very_unhealthy_desc),
            backgroundColor = Violate,
            icon=R.drawable.very_unhealthy
        )
        AQILevel(
            level = stringResource(id = R.string.aqi_leve_hazardous),
            levelDesc = stringResource(id = R.string.aqi_leve_hazardous_desc),
            backgroundColor = Pink,
            icon=R.drawable.gas_mask
        )
        Spacer(modifier = Modifier.padding(top = 100.dp))
    }

    @Composable
    fun AQILevel(
        level:String,
        levelDesc:String,
        backgroundColor:Color,
        icon:Int
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp),
        ) {
            Card(
                modifier = Modifier
                    .size(60.dp),
                backgroundColor = backgroundColor,
                elevation = 1.dp
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp),
                    tint = MaterialTheme.colors.onBackground.copy(0.4f)
                )
            }
            Column(
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(
                    text = level,
                    color = MaterialTheme.colors.onBackground.copy(0.6f),
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = levelDesc,
                    color = MaterialTheme.colors.onBackground.copy(0.5f),
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }
    }


}

