package com.qrolic.blueair.model.home

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class DailyModel (

    @SerializedName("o3")
    @Expose

    val o3: List<O31Model>? = null,

    @SerializedName("pm10")
    @Expose

    val pm10: List<O31Model>? = null,

    @SerializedName("pm25")
    @Expose

    val pm25: List<O31Model>? = null,

    @SerializedName("uvi")
    @Expose

    val uvi: List<O31Model>? = null,

    ):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(O31Model),
        parcel.createTypedArrayList(O31Model),
        parcel.createTypedArrayList(O31Model),
        parcel.createTypedArrayList(O31Model)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(o3)
        parcel.writeTypedList(pm10)
        parcel.writeTypedList(pm25)
        parcel.writeTypedList(uvi)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DailyModel> {
        override fun createFromParcel(parcel: Parcel): DailyModel {
            return DailyModel(parcel)
        }

        override fun newArray(size: Int): Array<DailyModel?> {
            return arrayOfNulls(size)
        }
    }

}