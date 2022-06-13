@file:OptIn(ExperimentalComposeUiApi::class)

package com.qrolic.blueair.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qrolic.blueair.R
import com.qrolic.blueair.activity.ui.theme.BlueAirTheme
import com.qrolic.blueair.components.EmptyDataLayout
import com.qrolic.blueair.database.MySharedPreferences
import com.qrolic.blueair.model.search.SearchModel
import com.qrolic.blueair.utils.*
import com.qrolic.blueair.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchActivity : ComponentActivity() {

    val viewModel: SearchViewModel by viewModels()

    @Inject
    lateinit var mySharedPreferences: MySharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setAnimation()
        super.onCreate(savedInstanceState)
        viewModel.searchList(mySharedPreferences.defaultCity)
        viewModel.message.observe(this@SearchActivity) {
            toast(it)
        }
        setContent {
            BlueAirTheme {
                SearchContent()
            }
        }

    }


    @Composable
    fun SearchContent() {
        val searchList = viewModel.searchList.observeAsState(emptyList())

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            horizontalAlignment = CenterHorizontally
        ) {
            TopAppBar()
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = CenterHorizontally
            ) {
                item {
                    if (searchList.value.isEmpty()) {
                        EmptyDataLayout(
                            Modifier
                                .fillParentMaxHeight(1f)
                                .align(CenterHorizontally)
                        )
                    }
                }
                items(
                    items = searchList.value,
                    itemContent = { item: SearchModel ->
                        SearchItem(item)
                    }
                )


            }

        }
        LoadingView(isDisplayed = viewModel.loading.value)
        ServerError(isDisplayed = viewModel.serverError.value)
        NetworkError(
            isDisplayed = viewModel.networkError.value,
            onClick = {
                    viewModel.networkError.value = false
                    viewModel.searchList("")
                    viewModel.message.observe(this) {
                        toast(it)
                    }
                }
        )
    }

    @Composable
    fun SearchItem(item: SearchModel) {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    viewModel.cityFeed(item.station?.name.toString())
                    viewModel.homeModel.observe(this) {
                        val intent = Intent(context, AirDetailActivity::class.java)
                        intent.putExtra("homeModel", it)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                        context.startActivity(intent)
                    }
                }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 15.dp)
            ) {
                val aqiVal = item.aqi ?: "0"
                val aqi = if (aqiVal == "-")
                    0
                else
                    aqiVal.toInt()
                val color = color(aqi)


                Card(
                    backgroundColor = color,
                    modifier = Modifier.width(80.dp)
                ) {
                    Text(
                        text = item.aqi ?: "-",
                        color = MaterialTheme.colors.onBackground.copy(0.5f),
                        fontSize = 16.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp),
                        textAlign = TextAlign.Center

                    )
                }
                Text(
                    text = item.station!!.name.toString(),
                    color = MaterialTheme.colors.onBackground.copy(0.5f),
                    fontSize = 14.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(start = 15.dp)
                )
            }

            Divider()
        }

    }

    @Composable
    fun TopAppBar() {
        val keyboardController = LocalSoftwareKeyboardController.current
        val search =  viewModel.search
        TopAppBar(
            backgroundColor = MaterialTheme.colors.background,
            elevation = 3.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                verticalAlignment = Alignment.CenterVertically
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
                TextField(
                    value = search.value,
                    onValueChange = {
                        search.value = it
                    },
                    placeholder = {
                        Text(
                            text = stringResource(R.string.search),
                            color = MaterialTheme.colors.onBackground.copy(0.5f),
                            modifier = Modifier
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.background,
                        textColor = MaterialTheme.colors.onBackground,
                        focusedIndicatorColor = MaterialTheme.colors.background,
                        unfocusedIndicatorColor = MaterialTheme.colors.background
                    ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            keyboardController?.hide()
                            viewModel.searchList(search.value)

                        }
                    )
                )

            }

        }
    }

}


