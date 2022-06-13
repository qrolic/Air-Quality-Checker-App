package com.qrolic.blueair.repository

import com.qrolic.blueair.utils.ApiInterface
import com.qrolic.blueair.utils.Constants
import javax.inject.Inject

class SearchRepository @Inject constructor(private val apiInterface: ApiInterface) {

    fun searchList(keyword:String) = apiInterface.search(Constants.TOKEN, keyword = keyword)

    fun cityFeed(city:String) = apiInterface.cityFeed(city,Constants.TOKEN)

}