package com.qrolic.blueair.model.search

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class SearchResponseModel {

    @SerializedName("status")
    @Expose
    val status: String? = null

    @SerializedName("data")
    @Expose
    val data: List<SearchModel>? = null


}