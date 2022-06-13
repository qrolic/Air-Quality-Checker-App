package com.qrolic.blueair.model.home

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class AttributionModel (
    @SerializedName("url")
    @Expose
    val url: String? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("logo")
    @Expose
    val logo: String? = null
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
        parcel.writeString(name)
        parcel.writeString(logo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AttributionModel> {
        override fun createFromParcel(parcel: Parcel): AttributionModel {
            return AttributionModel(parcel)
        }

        override fun newArray(size: Int): Array<AttributionModel?> {
            return arrayOfNulls(size)
        }
    }

}