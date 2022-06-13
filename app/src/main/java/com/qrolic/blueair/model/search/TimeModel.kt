package com.qrolic.blueair.model.search

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class TimeModel (
    @SerializedName("tz")
    @Expose
    private val tz: String? = null,

    @SerializedName("stime")
    @Expose
    private val stime: String? = null,

    @SerializedName("vtime")
    @Expose
    private val vtime: Int? = null
)