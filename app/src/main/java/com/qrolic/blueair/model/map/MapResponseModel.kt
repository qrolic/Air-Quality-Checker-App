package com.qrolic.blueair.model.map

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class MapResponseModel (

    @SerializedName("status")
    @Expose
    val status: String? = null,

    @SerializedName("data")
    @Expose
    val data: List<MapModel>? = null
)