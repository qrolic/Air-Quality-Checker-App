package com.qrolic.blueair.utils

import com.qrolic.blueair.model.home.HomeResponseModel
import com.qrolic.blueair.model.map.MapResponseModel
import com.qrolic.blueair.model.search.SearchResponseModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    // search list
    @GET(Constants.SEARCH_API)
    fun search(
        @Query("token") token: String,
        @Query("keyword") keyword:String,
    ): Call<SearchResponseModel>

    @GET(Constants.GEO_IP_BASED_LOCATION)
    fun geoIPBasedLocation(
        @Query("token") token: String
    ):Call<HomeResponseModel>

    @GET(Constants.CITY_FEED)
    fun cityFeed(
        @Path("city") city:String,
        @Query("token") token: String,
    ):Call<HomeResponseModel>

    @GET(Constants.MAP_LIST)
    fun mapList(
        @Query("latlng") latLng:String,
        @Query("networks") networks:String,
        @Query("token") token:String
    ):Call<MapResponseModel>

}