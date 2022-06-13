package com.qrolic.blueair.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.qrolic.blueair.model.home.HomeModel
import com.qrolic.blueair.model.home.HomeResponseModel
import com.qrolic.blueair.model.map.MapModel
import com.qrolic.blueair.model.map.MapResponseModel
import com.qrolic.blueair.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@SuppressWarnings("kotlin:S3776")
@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository):ViewModel() {

    var loading = mutableStateOf(false)
    var serverError = mutableStateOf(false)
    var networkError = mutableStateOf(false)
    var message = MutableLiveData<String>()
    var homeModel = MutableLiveData<HomeModel>()
    var mapModelList = MutableLiveData<List<MapModel>>()

    init{
        geoLocationIpBased()
    }

    fun geoLocationIpBased(){
        loading.value = true
        val response = homeRepository.geoLocationIpBased()
        response.enqueue(object : Callback<HomeResponseModel> {
            override fun onResponse(
                call: Call<HomeResponseModel>,
                response: Response<HomeResponseModel>
            ) {
                loading.value = false
                if(response.isSuccessful){
                    val responseModel = response.body()
                    if(responseModel != null) {
                        if (responseModel.status == "ok") {
                            if (responseModel.data != null) {
                                homeModel.postValue(responseModel.data!!)
                            }
                        } else {
                            message.postValue(responseModel.status ?: "Invalid key")
                        }
                    }
                }else{
                    serverError.value = true
                }
            }

            override fun onFailure(call: Call<HomeResponseModel>, t: Throwable) {
                loading.value = false
                if (t is IOException) {
                    networkError.value = true
                } else {
                    serverError.value = true
                }
            }
        })
    }

    fun getMapList(latLng:String){
        loading.value = true
        val response = homeRepository.mapList(latLng)
        response.enqueue(object : Callback<MapResponseModel> {
            override fun onResponse(
                call: Call<MapResponseModel>,
                response: Response<MapResponseModel>
            ) {
                loading.value = false
                if(response.isSuccessful){
                    val responseModel = response.body()
                    if(responseModel != null){
                        if(responseModel.status == "ok"){
                            if(responseModel.data != null){
                                mapModelList.postValue(responseModel.data!!)
                            }
                        }else{
                            message.postValue(responseModel.status?:"Invalid key")
                        }
                    }
                }else{
                    serverError.value = true
                }
            }

            override fun onFailure(call: Call<MapResponseModel>, t: Throwable) {
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