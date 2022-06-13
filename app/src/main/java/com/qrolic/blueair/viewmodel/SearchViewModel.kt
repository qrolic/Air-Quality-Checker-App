package com.qrolic.blueair.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonSyntaxException
import com.qrolic.blueair.model.home.HomeModel
import com.qrolic.blueair.model.home.HomeResponseModel
import com.qrolic.blueair.model.search.SearchModel
import com.qrolic.blueair.model.search.SearchResponseModel
import com.qrolic.blueair.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.IllegalStateException
import javax.inject.Inject

@SuppressWarnings("kotlin:S3776")
@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: SearchRepository) : ViewModel() {

    var loading = mutableStateOf(false)
    var serverError = mutableStateOf(false)
    var networkError = mutableStateOf(false)
    var message = MutableLiveData<String>()
    var searchList = MutableLiveData<List<SearchModel>>()
    val search = mutableStateOf("")
    var homeModel = MutableLiveData<HomeModel>()

    // station/city data
    fun cityFeed(city: String) {
        loading.value = true
        val response = repository.cityFeed(city)
        response.enqueue(object : Callback<HomeResponseModel> {
            override fun onResponse(
                call: Call<HomeResponseModel>,
                response: Response<HomeResponseModel>
            ) {
                loading.value = false
                if (response.isSuccessful) {
                    val responseModel = response.body()
                    if (responseModel != null) {
                        if (responseModel.status == "ok") {
                            if (responseModel.data != null) {
                                homeModel.postValue(responseModel.data!!)
                            }
                        } else {
                            message.postValue(responseModel.status ?: "Invalid key")
                        }
                    }
                } else {
                    serverError.value = true
                }
            }

            override fun onFailure(call: Call<HomeResponseModel>, t: Throwable) {
                loading.value = false
                if (t is IOException) {
                    networkError.value = true
                } else if (t is IllegalStateException || t is JsonSyntaxException) {
                    message.postValue("Details are not available")
                } else {
                    serverError.value = true
                }
            }

        })
    }


    // search stations
    fun searchList(keyword: String) {
        loading.value = true
        val response = repository.searchList(keyword)
        response.enqueue(object : Callback<SearchResponseModel> {
            override fun onResponse(
                call: Call<SearchResponseModel>,
                response: Response<SearchResponseModel>
            ) {
                loading.value = false
                if (response.isSuccessful) {
                    val responseModel = response.body()
                    if (responseModel != null) {
                        if (responseModel.status == "ok") {
                            searchList.postValue(responseModel.data)
                        } else {
                            message.postValue(responseModel.status ?: "Invalid key")
                        }
                    }
                } else {
                    serverError.value = true
                }
            }

            override fun onFailure(call: Call<SearchResponseModel>, t: Throwable) {
                loading.value = false
                if (t is IOException) {
                    networkError.value = true
                } else {
                    serverError.value = true
                }
            }

        })
    }

}