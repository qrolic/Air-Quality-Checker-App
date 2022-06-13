package com.qrolic.blueair.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.qrolic.blueair.R
import com.qrolic.blueair.activity.bottom_screens.AppTitle
import com.qrolic.blueair.activity.ui.theme.BlueAirTheme
import com.qrolic.blueair.activity.ui.theme.Green
import com.qrolic.blueair.activity.ui.theme.WhiteGradient
import com.qrolic.blueair.components.NormalText
import com.qrolic.blueair.model.PollutantModel
import com.qrolic.blueair.model.home.HomeModel
import com.qrolic.blueair.model.home.O31Model
import com.qrolic.blueair.utils.*
import com.qrolic.blueair.viewmodel.AirDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt

@AndroidEntryPoint
class AirDetailActivity : ComponentActivity() {

    private lateinit var homeModel: HomeModel
    val viewModel: AirDetailViewModel by viewModels()
    var color: Color? = null
    private var aqiVal = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeModel = intent.getParcelableExtra("homeModel")!!
        aqiVal = homeModel.aqi ?: 0
        color = color(aqiVal)
        setContent {
            BlueAirTheme {
                AirDetail()
            }
        }
    }

    @Composable
    fun LocationDetail() {
        Row(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 10.dp, start = 30.dp, end = 30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.location),
                contentDescription = "",
                tint = MaterialTheme.colors.onBackground.copy(0.7f),
                modifier = Modifier.size(25.dp)
            )
            NormalText(
                text = homeModel.city?.name.toString(),
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .padding(start = 15.dp)
                    .align(CenterVertically),
            )
        }
    }

    @Composable
    fun AQIDetail() {
        Box(
            modifier = Modifier.background(color ?: Green)
        ) {
            Box(
                modifier = Modifier.background(
                    brush = Brush.verticalGradient(
                        WhiteGradient
                    )
                )
            ) {
                Column {


                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        elevation = 1.dp,
                        backgroundColor = MaterialTheme.colors.background
                    ) {
                        Column(
                            horizontalAlignment = CenterHorizontally

                        ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(3.dp),
                                elevation = 1.dp,
                                backgroundColor = color ?: Green
                            ) {
                                val context = LocalContext.current
                                val aqiVal: Int = homeModel.aqi ?: 0
                                Row {
                                    Card(
                                        modifier = Modifier
                                            .height(100.dp)
                                            .fillMaxWidth()
                                            .weight(1f),
                                        backgroundColor = MaterialTheme.colors.onBackground.copy(
                                            0.1f
                                        ),
                                        elevation = 0.dp
                                    ) {
                                        Icon(
                                            painter = painterResource(id = context.faceMask(aqiVal)),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(15.dp),
                                            tint = MaterialTheme.colors.onBackground.copy(
                                                0.4f
                                            )
                                        )
                                    }
                                    Column(
                                        modifier = Modifier
                                            .wrapContentWidth()
                                            .weight(1f)
                                    ) {
                                        Text(
                                            text = homeModel.aqi.toString(),
                                            color = MaterialTheme.colors.onBackground.copy(
                                                0.5f
                                            ),
                                            modifier = Modifier.padding(top = 5.dp),
                                            fontWeight = FontWeight.Normal,
                                            fontFamily = FontFamily.SansSerif,
                                            fontSize = 40.sp
                                        )
                                        Text(
                                            text = "AQI",
                                            color = MaterialTheme.colors.onBackground.copy(
                                                0.5f
                                            ),
                                            modifier = Modifier.align(CenterHorizontally),
                                            fontWeight = FontWeight.Normal,
                                            fontFamily = FontFamily.SansSerif,
                                            fontSize = 12.sp
                                        )
                                    }
                                    Text(
                                        text = context.aqiDetail(aqiVal),
                                        color = MaterialTheme.colors.onBackground.copy(0.5f),
                                        modifier = Modifier
                                            .padding(top = 5.dp)
                                            .weight(1f)
                                            .align(CenterVertically),
                                        fontWeight = FontWeight.Normal,
                                        fontFamily = FontFamily.SansSerif,
                                        fontSize = 22.sp
                                    )
                                }
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 10.dp, vertical = 10.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                TemperatureDetail(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f),
                                    drawable = R.drawable.celsius,
                                    text = homeModel.iaqi?.t?.v?.roundToInt().toString() + "°"
                                )

                                val wind = homeModel.iaqi?.w?.v?.roundToInt()
                                TemperatureDetail(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f),
                                    drawable = R.drawable.wind,
                                    text = wind.toString() + " m/s"
                                )
                                val humidity = homeModel.iaqi?.h?.v?.roundToInt()
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
                    val context = LocalContext.current
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp, end = 15.dp, bottom = 15.dp)
                            .clickable {
                                val intent = Intent(context, AttributionActivity::class.java)
                                intent.putParcelableArrayListExtra(
                                    "attributionModel",
                                    homeModel.attributions as ArrayList<out Parcelable>
                                )
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                                startActivity(intent)
                            },
                        elevation = 1.dp,
                        backgroundColor = MaterialTheme.colors.background
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 15.dp, vertical = 15.dp)
                        ) {
                            NormalText(
                                text = "Attributions",
                                color = MaterialTheme.colors.onBackground,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)

                            )
                            Icon(
                                imageVector = Icons.Outlined.KeyboardArrowRight,
                                contentDescription = "",
                                modifier = Modifier.size(25.dp),
                                tint = MaterialTheme.colors.onBackground.copy(0.5f)
                            )
                        }
                    }
                    Text(
                        text = homeModel.time?.s.toString(),
                        color = MaterialTheme.colors.onBackground.copy(0.5f),
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 15.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

    @Composable
    fun TemperatureDetail(
        modifier: Modifier,
        drawable: Int,
        text: String
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = drawable),
                contentDescription = "",
                tint = MaterialTheme.colors.onBackground.copy(0.6f),
                modifier = Modifier.size(25.dp)
            )
            NormalText(
                text = text,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.padding(start = 15.dp)
            )
        }
    }

    @Composable
    fun AirDetail() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            TopAppBar()
            LazyColumn(
                modifier = Modifier,
                horizontalAlignment = CenterHorizontally
            ) {
                item {
                    LocationDetail()
                    Image(
                        painter = painterResource(id = R.drawable.city),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        colorFilter = ColorFilter.tint(color ?: Green),
                        contentScale = ContentScale.Crop
                    )
                    AQIDetail()
                    Divider(
                        modifier = Modifier.height(5.dp),
                        color = MaterialTheme.colors.surface.copy(0.5f)
                    )
                    HealthRecommendation()
                    Divider(
                        modifier = Modifier.height(5.dp),
                        color = MaterialTheme.colors.surface.copy(0.5f)
                    )
                    PollutantDetail()
                    Divider(
                        modifier = Modifier.height(5.dp),
                        color = MaterialTheme.colors.surface.copy(0.5f)
                    )
                    ForecastDetail()
                }
            }
        }
    }

    @Composable
    fun ForecastDetail() {
        NormalText(
            text = stringResource(id = R.string.forecast),
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .padding(vertical = 25.dp, horizontal = 15.dp)
        )
        SelectChartTitle()
        NormalText(text = "")
        viewModel.forecastList.value = homeModel.forecast?.daily?.o3!!
        LineChart()

    }


    @Composable
    private fun LineChart() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
        ) {

            val foreCastList = viewModel.forecastList.observeAsState(emptyList())
            AndroidView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp),
                factory = { context ->
                    com.github.mikephil.charting.charts.LineChart(context)
                },
                update = { chart ->
                    // Chart properties
                    chart.description.isEnabled = false
                    chart.axisLeft.setDrawGridLines(false)
                    chart.xAxis.setDrawGridLines(false)
                    chart.setTouchEnabled(true)
                    chart.isDragEnabled = true
                    chart.setScaleEnabled(true)

                    // Group chart data from data
                    val chartData = getData(foreCastList.value)

                    //Set the data set for the line
                    val lineDataSet = LineDataSet(
                        chartData.entries,
                        "Average Values"
                    )
                    lineDataSet.setDrawCircles(false)
                    lineDataSet.color = ContextCompat.getColor(chart.context, R.color.teal_200)
                    lineDataSet.valueTextColor =
                        ContextCompat.getColor(chart.context, R.color.teal_200)
                    lineDataSet.setDrawCircles(true)
                    lineDataSet.setGradientColor(
                        android.graphics.Color.BLACK,
                        android.graphics.Color.RED
                    )
                    lineDataSet.lineWidth = 1.75f
                    lineDataSet.circleRadius = 5f
                    lineDataSet.circleHoleRadius = 1.5f
                    lineDataSet.valueTextColor =
                        ContextCompat.getColor(chart.context, R.color.black)
                    lineDataSet.valueTextSize = 14f
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                        lineDataSet.setDrawFilled(true)
                        val fillGradient = ContextCompat.getDrawable(
                            this@AirDetailActivity,
                            R.drawable.chart_gradient
                        )
                        lineDataSet.fillDrawable = fillGradient
                    }


                    //x-axis properties
                    val xAxis = chart.xAxis
                    xAxis.granularity = 1f
                    xAxis.textSize = 12f
                    xAxis.textColor =
                        ContextCompat.getColor(chart.context, R.color.black)
                    xAxis.position = XAxis.XAxisPosition.BOTTOM
                    val formatter = object : ValueFormatter() {
                        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                            return if (value.toInt() < chartData.xAxisValues.size) chartData.xAxisValues[value.toInt()] else ""
                        }
                    }
                    xAxis.valueFormatter = formatter
                    xAxis.labelRotationAngle = 90f

                    // y-axis properties
                    val yAxisRight = chart.axisRight
                    yAxisRight.isEnabled = false

                    val yAxisLeft = chart.axisLeft
                    yAxisLeft.granularity = 1f
                    yAxisLeft.textSize = 12f
                    yAxisLeft.textColor =
                        ContextCompat.getColor(chart.context, R.color.black)

                    //Set the data for the line chart
                    val data = LineData(lineDataSet)
                    chart.data = data
                    chart.animateX(500)

                    //Update the legend for the chart
                    val legend = chart.legend
                    legend.textColor =
                        ContextCompat.getColor(chart.context, R.color.black)
                    legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
                    //Draw the chart
                    chart.invalidate()


                })
        }
    }

    private fun getData(forecastList: List<O31Model>): ChartData {
        val xAxisValues = arrayListOf<String>() // x-axis labels
        val entries = arrayListOf<Entry>() // entries for plotting

        forecastList.forEachIndexed { index: Int, o31Model: O31Model ->
            val date = serverDateFormat.parse(o31Model.day.toString())

            xAxisValues.add(simpleDateFormat.format(date!!))
            entries.add(Entry(index.toFloat(), o31Model.avg?.toFloat()!!))
        }

        return ChartData(xAxisValues, entries)
    }

    @Composable
    private fun SelectChartTitle() {
        val list = listOf("O3", "PM10", "PM25", "UVI")
        val title = remember { mutableStateOf(list[0]) }
        val selectedTitle = title.value
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
        ) {
            items(
                items = list,
                itemContent = { s: String ->
                    TitleItem(
                        s = s,
                        isSelected = selectedTitle == s,
                        onSelectedTitleChanged = {
                            title.value = s
                        }
                    )

                }
            )
        }
    }

    @Composable
    private fun TitleItem(
        s: String,
        isSelected: Boolean = false,
        onSelectedTitleChanged: (String) -> Unit
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 5.dp, vertical = 10.dp)
                .clip(MaterialTheme.shapes.small)
                .background(if (isSelected) color!! else MaterialTheme.colors.surface)
                .toggleable(
                    value = isSelected,
                    onValueChange = {
                        onSelectedTitleChanged(s)
                        when (s) {
                            "O3" -> {
                                viewModel.forecastList.value = homeModel.forecast?.daily?.o3!!

                            }
                            "PM10" -> {
                                viewModel.forecastList.value = homeModel.forecast?.daily?.pm10!!
                            }
                            "PM25" -> {
                                viewModel.forecastList.value = homeModel.forecast?.daily?.pm25!!
                            }
                            "UVI" -> {
                                viewModel.forecastList.value = homeModel.forecast?.daily?.uvi!!
                            }
                        }
                    }
                )
        ) {
            Text(
                text = s,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(horizontal = 15.dp, vertical = 10.dp),
                fontSize = 14.sp,
                fontFamily = FontFamily.SansSerif
            )
        }

    }

    @Composable
    fun PollutantDetail() {
        NormalText(
            text = stringResource(id = R.string.pollutant),
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .padding(vertical = 25.dp, horizontal = 15.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            PollutantItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp, end = 5.dp)
                    .weight(1f)
                    .clickable {
                        val causeList = listOf(
                            R.string.pm_2_5_cause_1,
                            R.string.pm_2_5_cause_2,
                            R.string.pm_2_5_cause_3,
                            R.string.pm_2_5_cause_4,
                        )
                        val shortTermList = listOf(
                            R.string.pm_2_5_shor_term_1,
                            R.string.pm_2_5_shor_term_2,
                            R.string.pm_2_5_shor_term_3,
                            R.string.pm_2_5_shor_term_4
                        )
                        val longTermList = listOf(
                            R.string.pm_2_5_long_term_1,
                            R.string.pm_2_5_long_term_2,
                            R.string.pm_2_5_long_term_3,
                            R.string.pm_2_5_long_term_4,
                            R.string.pm_2_5_long_term_5,
                            R.string.pm_2_5_long_term_6
                        )
                        val pollutantModel = PollutantModel(
                            R.string.pm_2_5,
                            R.string.pm_2_5_desc,
                            shortTermList,
                            longTermList,
                            causeList,
                        )
                        val intent =
                            Intent(this@AirDetailActivity, PollutantDetailActivity::class.java)
                        intent.putExtra("pollutantModel", pollutantModel)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)

                    },
                name = "PM2.5",
                value = homeModel.iaqi?.pm25?.v.toString()
            )
            PollutantItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp, start = 5.dp)
                    .weight(1f)
                    .clickable {
                        val causeList = listOf(
                            R.string.pm_10_cause_1,
                            R.string.pm_10_cause_2,
                            R.string.pm_10_cause_3,
                            R.string.pm_10_cause_4,
                            R.string.pm_10_cause_5,
                        )
                        val shortTermList = listOf(
                            R.string.pm_10_shor_term_1,
                            R.string.pm_10_shor_term_2,
                            R.string.pm_10_shor_term_3,
                            R.string.pm_10_shor_term_4,
                            R.string.pm_10_shor_term_5
                        )
                        val longTermList = listOf(
                            R.string.pm_10_long_term_1,
                            R.string.pm_10_long_term_2,
                            R.string.pm_10_long_term_3,
                            R.string.pm_10_long_term_4,
                        )
                        val pollutantModel = PollutantModel(
                            R.string.pm_10,
                            R.string.pm_10_desc,
                            shortTermList,
                            longTermList,
                            causeList,
                        )
                        val intent =
                            Intent(this@AirDetailActivity, PollutantDetailActivity::class.java)
                        intent.putExtra("pollutantModel", pollutantModel)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)

                    },
                name = "PM10",
                value = homeModel.iaqi?.pm10?.v.toString()
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            PollutantItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp, end = 5.dp)
                    .weight(1f)
                    .clickable {
                        val causeList = listOf(
                            R.string.o3_cause_1,
                        )
                        val shortTermList = listOf(
                            R.string.o3_shor_term_1,
                            R.string.o3_shor_term_2,
                            R.string.o3_shor_term_3,
                        )
                        val longTermList = listOf(
                            R.string.o3_long_term_1,
                            R.string.o3_long_term_2,
                            R.string.o3_long_term_3,
                        )
                        val pollutantModel = PollutantModel(
                            R.string.o3,
                            R.string.o3_desc,
                            shortTermList,
                            longTermList,
                            causeList,
                        )
                        val intent =
                            Intent(this@AirDetailActivity, PollutantDetailActivity::class.java)
                        intent.putExtra("pollutantModel", pollutantModel)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)

                    },
                name = "O3",
                value = homeModel.iaqi?.o3?.v.toString()
            )
            PollutantItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp, start = 5.dp)
                    .weight(1f)
                    .clickable {
                        val causeList = listOf(
                            R.string.no2_cause_1,
                            R.string.no2_cause_2,
                        )
                        val shortTermList = listOf(
                            R.string.no2_shor_term_1,
                            R.string.no2_shor_term_2,
                        )
                        val longTermList = listOf(
                            R.string.no2_long_term_1,
                            R.string.no2_long_term_2,
                            R.string.no2_long_term_3,
                        )
                        val pollutantModel = PollutantModel(
                            R.string.no2,
                            R.string.no2_desc,
                            shortTermList,
                            longTermList,
                            causeList,
                        )
                        val intent =
                            Intent(this@AirDetailActivity, PollutantDetailActivity::class.java)
                        intent.putExtra("pollutantModel", pollutantModel)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)

                    },
                name = "NO2",
                value = homeModel.iaqi?.no2?.v.toString()
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            PollutantItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp, end = 5.dp)
                    .weight(1f)
                    .clickable {
                        val causeList = listOf(
                            R.string.so2_cause_1,
                            R.string.so2_cause_2,
                        )
                        val shortTermList = listOf(
                            R.string.so2_shor_term_1,
                            R.string.so2_shor_term_2,
                            R.string.so2_long_term_3,
                        )
                        val longTermList = listOf(
                            R.string.so2_long_term_1,
                            R.string.so2_long_term_2,
                            R.string.so2_long_term_3,
                        )
                        val pollutantModel = PollutantModel(
                            R.string.so2,
                            R.string.so2_desc,
                            shortTermList,
                            longTermList,
                            causeList,
                        )
                        val intent =
                            Intent(this@AirDetailActivity, PollutantDetailActivity::class.java)
                        intent.putExtra("pollutantModel", pollutantModel)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)

                    },
                name = "SO2",
                value = homeModel.iaqi?.so2?.v.toString()
            )
            PollutantItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp, start = 5.dp)
                    .weight(1f)
                    .clickable {
                        val causeList = listOf(
                            R.string.co_cause_1,
                        )
                        val shortTermList = listOf(
                            R.string.co_shor_term_1,
                            R.string.co_shor_term_2,
                            R.string.co_shor_term_3,
                        )
                        val longTermList = listOf(
                            R.string.co_long_term_1,
                        )
                        val pollutantModel = PollutantModel(
                            R.string.co,
                            R.string.co_desc,
                            shortTermList,
                            longTermList,
                            causeList,
                        )
                        val intent =
                            Intent(this@AirDetailActivity, PollutantDetailActivity::class.java)
                        intent.putExtra("pollutantModel", pollutantModel)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)

                    },
                name = "CO",
                value = homeModel.iaqi?.co?.v.toString()
            )
        }
    }

    @Composable
    fun PollutantItem(
        modifier: Modifier,
        name: String,
        value: String,
    ) {
        Card(
            modifier = modifier,
            backgroundColor = MaterialTheme.colors.background,
            elevation = 5.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = name,
                        color = MaterialTheme.colors.onBackground,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                    Text(
                        text = "µg/m3",
                        color = MaterialTheme.colors.onBackground.copy(0.5f),
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                }
                Text(
                    text = value,
                    color = MaterialTheme.colors.onBackground.copy(0.5f),
                    fontWeight = FontWeight.Normal,
                    fontSize = 22.sp,
                    fontFamily = FontFamily.SansSerif
                )
            }
        }
    }

    @Composable
    fun HealthRecommendation() {
        NormalText(
            text = stringResource(id = R.string.health_recommendation),
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .padding(vertical = 25.dp, horizontal = 15.dp)
        )
        val s = remember { mutableStateOf(cycling(aqiVal)) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.cycling), contentDescription = "",
                tint = color ?: Green,
                modifier = Modifier
                    .size(70.dp)
                    .padding(8.dp)
                    .weight(1f)
                    .clickable {
                        s.value = cycling(aqiVal)

                    }
            )
            Icon(
                painter = painterResource(id = R.drawable.window), contentDescription = "",
                tint = color ?: Green,
                modifier = Modifier
                    .size(70.dp)
                    .padding(8.dp)
                    .weight(1f)
                    .clickable {
                        s.value = window(aqiVal)
                    }
            )
            Icon(
                painter = painterResource(id = R.drawable.mask2), contentDescription = "",
                tint = color ?: Green,
                modifier = Modifier
                    .size(70.dp)
                    .padding(8.dp)
                    .weight(1f)
                    .clickable {
                        s.value = mask(aqiVal)
                    }
            )
            Icon(
                painter = painterResource(id = R.drawable.air_purifier), contentDescription = "",
                tint = color ?: Green,
                modifier = Modifier
                    .size(60.dp)
                    .padding(8.dp)
                    .weight(1f)
                    .clickable {
                        s.value = airPurifier(aqiVal)
                    }
            )
        }
        Box(
            modifier = Modifier
                .padding(vertical = 15.dp, horizontal = 15.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(color?.copy(0.2f) ?: Green.copy(0.2f))
        ) {
            NormalText(
                text = (s.value),
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .padding(10.dp),
                fontSize = 14.sp
            )
        }

    }

    @Composable
    fun TopAppBar() {
        TopAppBar(
            backgroundColor = MaterialTheme.colors.background,
            elevation = 3.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                verticalAlignment = CenterVertically
            ) {
                IconButton(onClick = {
                    finishAfterTransition()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = "",
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier.size(30.dp)
                    )
                }
                AppTitle(
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
                IconButton(onClick = {
                    openActivity(AirGuideActivity())
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.info),
                        contentDescription = "",
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
}



