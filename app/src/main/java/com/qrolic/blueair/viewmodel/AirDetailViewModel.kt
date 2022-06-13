package com.qrolic.blueair.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.qrolic.blueair.model.home.O31Model

class AirDetailViewModel :ViewModel() {

    var forecastList = MutableLiveData<List<O31Model>>()

}