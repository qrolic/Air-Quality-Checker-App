package com.qrolic.blueair.activity

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.qrolic.blueair.R
import com.qrolic.blueair.activity.ui.theme.BlueAirTheme
import com.qrolic.blueair.activity.ui.theme.Gradient
import com.qrolic.blueair.components.*
import com.qrolic.blueair.database.MySharedPreferences
import com.qrolic.blueair.utils.openActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var mySharedPreferences: MySharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlueAirTheme {
                Navigation()
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Navigation() {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = "splash_screen",
            modifier = Modifier
                .fillMaxSize()
                .background(brush = Brush.verticalGradient(colors = Gradient))
        ) {
            composable("splash_screen") {
                SplashScreen(navController = navController)
            }
            if(mySharedPreferences.isFirstTimeInstall){
                composable("main_screen") {
                    WalkThroughForm()
                }
            }else{
                openActivity(HomeActivity())
                overridePendingTransition(0,0)
                finish()
            }



        }
    }

    @Composable
    fun WalkThroughForm() {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = CenterHorizontally
            ) {
                DrawableImage(
                    drawable = R.drawable.appicon,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(horizontal = 80.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary)
                )
                TitleText(
                    text = stringResource(id = R.string.breath_better_everywhere),
                    modifier = Modifier
                        .padding(top = 70.dp, start = 20.dp, end = 20.dp)
                )
                LightText(
                    text = stringResource(id = R.string.breath_better_desc),
                    modifier = Modifier
                        .padding(20.dp)
                        .align(CenterHorizontally)
                )
                val context = LocalContext.current
                WhiteButton(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(horizontal = 20.dp, vertical = 30.dp)
                        .align(CenterHorizontally)
                        .clip(RoundedCornerShape(15.dp)),
                    text = stringResource(R.string.get_started),
                    textModifier = Modifier.padding(10.dp)
                ) {
                    mySharedPreferences.isFirstTimeInstall = false
                    context.openActivity(HomeActivity())
                    overridePendingTransition(0,0)
                    finish()
                }
            }


    }

    @Composable
    fun SplashScreen(navController: NavController) {
        val scale = remember {
            Animatable(0f)
        }
        LaunchedEffect(key1 = true) {
            scale.animateTo(
                targetValue = 0.8f,
                animationSpec = tween(
                    durationMillis = 600,
                    easing = {
                        OvershootInterpolator(2f).getInterpolation(it)
                    })
            )
            delay(1000L)
            navController.navigate("main_screen") {
                popUpTo("splash_screen") {
                    inclusive = true
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .scale(scale.value)
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                DrawableImage(
                    drawable = R.drawable.appicon,
                    modifier = Modifier
                        .size(70.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary)
                )
                Text(
                    text = stringResource(id = R.string.app_name),
                    color = MaterialTheme.colors.background,
                    fontSize = 40.sp,
                    modifier = Modifier.padding(start = 15.dp),
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.SansSerif
                )
            }
            NormalText(
                text = stringResource(id = R.string.tag),
                color = MaterialTheme.colors.background,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .scale(scale.value),
            )
        }

    }


}

