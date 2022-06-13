package com.qrolic.blueair.model.map

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class MapModel (

    @SerializedName("lat")
    @Expose
    val lat: Double? = null,

    @SerializedName("lon")
    @Expose
    val lon: Double? = null,

    @SerializedName("uid")
    @Expose
    val uid: Int? = null,

    @SerializedName("aqi")
    @Expose
    val aqi: String? = null,

    @SerializedName("station")
    @Expose
    val station: StationModel? = null,
)