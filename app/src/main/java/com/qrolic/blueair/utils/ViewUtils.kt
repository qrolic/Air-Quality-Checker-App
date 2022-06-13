package com.qrolic.blueair.utils

import android.content.Context
import android.content.Intent
import android.transition.Slide
import android.view.Gravity
import android.view.Window
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.Color
import com.github.mikephil.charting.data.Entry
import com.qrolic.blueair.R
import com.qrolic.blueair.activity.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

fun Context.openActivity(activity:ComponentActivity){
    val intent = Intent(this,activity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
    startActivity(intent)
}

fun Context.toast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_LONG).show()
}

fun Window.setAnimation() {
    val slide = Slide()
    slide.slideEdge = Gravity.START
    slide.duration = 400
    slide.interpolator = DecelerateInterpolator()
    exitTransition = slide
    enterTransition = slide
}

fun Context.cycling(aqi: Int):String{
    return when (aqi) {
        in 0..50 -> {
            "Enjoy outdoor activities"
        }
        in 51..100 -> {
            "Sensitive group should reduce outdoor exercise"
        }
        in 101..150 -> {
            "Everyone should reduce outdoor exercise"
        }
        in 151..200 -> {
            "Avoid outdoor exercise "
        }
        in 201..300 -> {
            "Avoid outdoor exercise"
        }
        else -> {
            "Avoid outdoor exercise"
        }
    }
}

fun Context.window(aqi: Int):String{
    return when (aqi) {
        in 0..50 -> {
            "Open your windows to bring clean,fresh air indoors"
        }
        in 51..100 -> {
            " Close your windows to avoid dirty outdoor air "
        }
        in 101..150 -> {
            "Close your windows to avoid dirty outdoor air "
        }
        in 151..200 -> {
            "Close your windows to avoid dirty outdoor air "
        }
        in 201..300 -> {
            "Close your windows to avoid dirty outdoor air"
        }
        else -> {
            "Close your windows to avoid dirty outdoor air"
        }
    }
}

fun Context.mask(aqi: Int):String{
    return when (aqi) {
        in 0..50 -> {
            ""
        }
        in 51..100 -> {
            ""
        }
        in 101..150 -> {
            "Sensitive groups should wear a mask outdoors"
        }
        in 151..200 -> {
            "Wear a mask outdoors "
        }
        in 201..300 -> {
            "Wear a mask outdoors"
        }
        else -> {
            "Wear a mask outdoors"
        }
    }
}

fun Context.airPurifier(aqi: Int):String{
    return when (aqi) {
        in 0..50 -> {
            ""
        }
        in 51..100 -> {
            ""
        }
        in 101..150 -> {
            "Run an air purifier "
        }
        in 151..200 -> {
            "Run an air purifier "
        }
        in 201..300 -> {
            "Run an air purifier"
        }
        else -> {
            "Run an air purifier"
        }
    }
}

fun Context.color(aqi:Int):Color {
    return when (aqi) {
        in 0..50 -> {
            Green
        }
        in 51..100 -> {
            Yellow
        }
        in 101..150 -> {
            Orange
        }
        in 151..200 -> {
            Red
        }
        in 201..300 -> {
            Violate
        }
        else -> {
            Pink
        }
    }
}

fun Context.marker(aqi:Int):Int {
    return when (aqi) {
        in 0..50 -> {
            R.drawable.green_location
        }
        in 51..100 -> {
            R.drawable.yellow_locatio
        }
        in 101..150 -> {
            R.drawable.orange_location
        }
        in 151..200 -> {
            R.drawable.red_location
        }
        in 201..300 -> {
            R.drawable.violate_location
        }
        else -> {
            R.drawable.pink_location
        }
    }
}

fun Context.faceMask(aqi:Int):Int {
    return when (aqi) {
        in 0..50 -> {
            R.drawable.good
        }
        in 51..100 -> {
            R.drawable.moderate
        }
        in 101..150 -> {
            R.drawable.unhealthy
        }
        in 151..200 -> {
            R.drawable.mask2
        }
        in 201..300 -> {
            R.drawable.very_unhealthy
        }
        else -> {
            R.drawable.gas_mask
        }
    }
}

fun Context.aqiDetail(aqi:Int):String {
    return when (aqi) {
        in 0..50 -> {
            "Good"
        }
        in 51..100 -> {
            "Moderate"
        }
        in 101..150 -> {
            "Unhealthy for sensitive groups"
        }
        in 151..200 -> {
            "Unhealthy"
        }
        in 201..300 -> {
            "Very unhealthy"
        }
        else -> {
            "Hazardous"
        }
    }
}

val serverDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
val simpleDateFormat = SimpleDateFormat("MMMM dd", Locale.US)

data class ChartData(val xAxisValues: List<String>, val entries: List<Entry>)
