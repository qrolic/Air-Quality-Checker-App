package com.qrolic.blueair.model.search

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class SearchModel (

    @SerializedName("uid")
    @Expose
    private val uid: Int? = null,

    @SerializedName("aqi")
    @Expose
    val aqi: String? = null,

    @SerializedName("time")
    @Expose
    val time: TimeModel? = null,

    @SerializedName("station")
    @Expose
    val station: StationModel? = null

)