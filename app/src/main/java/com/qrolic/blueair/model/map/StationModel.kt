package com.qrolic.blueair.model.map

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class StationModel (

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("time")
    @Expose
    val time: String? = null
)