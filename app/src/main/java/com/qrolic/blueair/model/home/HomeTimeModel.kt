package com.qrolic.blueair.model.home

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class HomeTimeModel (

    @SerializedName("s")
    @Expose
    val s: String? = null,

    @SerializedName("tz")
    @Expose
    val tz: String? = null,

    @SerializedName("v")
    @Expose
    val v: Int? = null,

    @SerializedName("iso")
    @Expose
    val iso: String? = null,
    ):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(s)
        parcel.writeString(tz)
        parcel.writeValue(v)
        parcel.writeString(iso)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HomeTimeModel> {
        override fun createFromParcel(parcel: Parcel): HomeTimeModel {
            return HomeTimeModel(parcel)
        }

        override fun newArray(size: Int): Array<HomeTimeModel?> {
            return arrayOfNulls(size)
        }
    }

}