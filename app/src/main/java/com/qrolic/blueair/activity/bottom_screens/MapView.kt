package com.qrolic.blueair.activity.bottom_screens

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.qrolic.blueair.model.map.MapModel
import com.qrolic.blueair.utils.marker
import com.qrolic.blueair.viewmodel.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun bitmapDescriptorFromVector(
    context: Context,
    vectorResId: Int
): BitmapDescriptor? {

    // retrieve the actual drawable
    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    val bm = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    // draw it onto the bitmap
    val canvas = android.graphics.Canvas(bm)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bm)
}

@Composable
fun MapView(
    viewModel: HomeViewModel
) {
    val map = rememberMapViewWithLifecycle()
    // AndroidView() is a Composable that can be used to add Android Views
    val mapList = viewModel.mapModelList.observeAsState(emptyList())
    val context = LocalContext.current

    LaunchedEffect(map) {
        delay(1000)
        viewModel.isMapLoading.value = false
    }

    Box {
        // used to add android view
        AndroidView(
            { map },
            modifier = Modifier
                .fillMaxSize()
        ) { mapView ->
            CoroutineScope(Dispatchers.Main).launch {
                mapView.getMapAsync {
                    viewModel.getMapList("-85,-180,85,180")
                    // india
                    // viewModel.getMapList("8.4,68.7,37.6,97.25")
                    mapList.value.forEach { mapModel: MapModel ->
                        val location = LatLng(mapModel.lat ?: 0.0, mapModel.lon ?: 0.0)
                        var aqi = 0
                        if (mapModel.aqi != null && mapModel.aqi != "-") {
                            aqi = mapModel.aqi.toInt()
                        }
                        val drawable = context.marker(aqi)
                        val icon = bitmapDescriptorFromVector(
                            context, drawable
                        )
                        it.addMarker(
                            MarkerOptions().position(location).icon(icon)
                                .title(mapModel.station?.name.toString()).snippet(
                                "$aqi Aqi"
                            )
                        )
                    }
                }
            }
        }
    }
    if (viewModel.isMapLoading.value) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }


}


@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context)
    }

    // Makes MapView follow the lifecycle of this composable
    val lifecycleObserver = rememberMapLifecycleObserver(mapView)
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }

    return mapView
}

@Composable
private fun rememberMapLifecycleObserver(mapView: MapView): LifecycleEventObserver =
    remember(mapView) {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
                Lifecycle.Event.ON_START -> mapView.onStart()
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                Lifecycle.Event.ON_STOP -> mapView.onStop()
                Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                else -> throw IllegalStateException()
            }
        }
    }