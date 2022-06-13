package com.qrolic.blueair.model.search

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class StationModel (
    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("geo")
    @Expose
    private val geo: List<Double>? = null,

    @SerializedName("url")
    @Expose
    private val url: String? = null,

    @SerializedName("country")
    @Expose
    private val country: String? = null
)