package com.qrolic.blueair.activity.bottom_screens

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LifecycleOwner
import com.qrolic.blueair.R
import com.qrolic.blueair.activity.AirDetailActivity
import com.qrolic.blueair.activity.SearchActivity
import com.qrolic.blueair.components.CapitalText
import com.qrolic.blueair.components.DrawableImage
import com.qrolic.blueair.components.NormalText
import com.qrolic.blueair.components.SmallText
import com.qrolic.blueair.model.home.HomeModel
import com.qrolic.blueair.utils.*
import com.qrolic.blueair.viewmodel.HomeViewModel
import kotlin.math.roundToInt

@Composable
fun MyAir(activity: Activity, viewModel: HomeViewModel, owner: LifecycleOwner) {
    val context = LocalContext.current
    val model = viewModel.homeModel.observeAsState(null)
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(activity)
        LazyColumn {
            item {
                AirDetail(model = model.value)
            }
        }
    }

    LoadingView(isDisplayed = viewModel.loading.value)
    ServerError(isDisplayed = viewModel.serverError.value)
    NetworkError(
        isDisplayed = viewModel.networkError.value,
        onClick = {
                viewModel.networkError.value = false
                viewModel.geoLocationIpBased()
                viewModel.message.observe(owner) {
                    context.toast(it)
                }
            }
    )
}

@Composable
fun AirDetail(
    model: HomeModel?
) {
    val context = LocalContext.current
    var aqiVal = 0
    if (model != null) {
        aqiVal = model.aqi ?: 0
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .clickable {
                val intent = Intent(context, AirDetailActivity::class.java)
                intent.putExtra("homeModel", model)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                context.startActivity(intent)
            },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {

            AQIData(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.5f)
                    .background(context.color(aqiVal)),
                model = model
            )
            OtherData(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                model = model
            )
        }
    }
}

@Composable
fun OtherData(modifier: Modifier, model: HomeModel?) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp, vertical = 10.dp),
    ) {

        Text(
            text = model?.city?.name.toString(),
            color = MaterialTheme.colors.onBackground.copy(0.7f),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        )
        Text(
            text = model?.time?.s.toString(),
            color = MaterialTheme.colors.onBackground.copy(0.5f),
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            modifier = Modifier.padding(5.dp)
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TemperatureDetail(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                drawable = R.drawable.pressure,
                text = model?.iaqi?.p?.v?.roundToInt().toString()+" mb"
            )

            val wind = model?.iaqi?.w?.v?.roundToInt()
            TemperatureDetail(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                drawable = R.drawable.wind,
                text = wind.toString() + " m/s"
            )
            val humidity = model?.iaqi?.h?.v?.roundToInt()
            TemperatureDetail(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                drawable = R.drawable.humidity,
                text = humidity.toString() + " %"
            )
        }
    }

}

@Composable
fun TemperatureDetail(
    modifier: Modifier,
    drawable: Int,
    text: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = drawable),
            contentDescription = "",
            tint = MaterialTheme.colors.onBackground.copy(0.4f),
            modifier = Modifier.size(25.dp)
        )
        NormalText(
            text = text,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding()
        )
    }
}

@Composable
fun AQIData(
    modifier: Modifier,
    model: HomeModel?
) {
    Row(modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black.copy(0.1f))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painterResource(id = R.drawable.celsius),
                        contentDescription = "",
                        tint = MaterialTheme.colors.background,
                        modifier = Modifier
                            .size(25.dp)
                            .rotate(0f)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    NormalText(
                        text = model?.iaqi?.t?.v?.roundToInt().toString() + "°",
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            }
            val context = LocalContext.current
            var aqiVal = 0
            if (model != null) {
                aqiVal = model.aqi ?: 0
            }

            Text(
                text = context.aqiDetail(aqiVal),
                color = MaterialTheme.colors.onBackground.copy(0.5f),
                modifier = Modifier.padding(top = 5.dp),
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif,
                fontSize = 18.sp
            )

            val icon = context.faceMask(aqi = aqiVal)
            Icon(
                painterResource(id = icon),
                contentDescription = "",
                tint = MaterialTheme.colors.onBackground.copy(0.5f),
                modifier = Modifier
                    .size(60.dp)
                    .padding(top = 5.dp)
            )
            Text(
                text = model?.aqi.toString(),
                color = MaterialTheme.colors.onBackground.copy(0.5f),
                modifier = Modifier.padding(top = 5.dp),
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily.SansSerif,
                fontSize = 28.sp
            )
            SmallText(
                text = "AQI",
                color = MaterialTheme.colors.onBackground.copy(0.5f),
                modifier = Modifier.padding(bottom = 5.dp)
            )
        }
    }
}

@Composable
fun TopAppBar(activity: Activity) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppTitle(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            val context = LocalContext.current
            IconButton(onClick = {
                val intent = Intent(context, SearchActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(
                    context,
                    intent,
                    ActivityOptions.makeSceneTransitionAnimation(activity).toBundle()
                )
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "",
                    tint = MaterialTheme.colors.onBackground,
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}

@Composable
fun AppTitle(
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        DrawableImage(
            drawable = R.drawable.appicon,
            modifier = Modifier
                .size(25.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
        )
        CapitalText(
            text = stringResource(id = R.string.app_name),
            color = MaterialTheme.colors.primary.copy(),
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

