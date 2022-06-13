package com.qrolic.blueair.repository

import com.qrolic.blueair.utils.ApiInterface
import com.qrolic.blueair.utils.Constants
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiInterface: ApiInterface) {

    fun geoLocationIpBased() = apiInterface.geoIPBasedLocation(Constants.TOKEN)

    fun mapList(latLng:String) = apiInterface.mapList(latLng,Constants.NETWORK_ALL,Constants.TOKEN)

}