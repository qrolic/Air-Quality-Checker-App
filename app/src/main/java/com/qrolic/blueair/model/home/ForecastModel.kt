package com.qrolic.blueair.model.home

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class ForecastModel (

    @SerializedName("daily")
    @Expose
    val daily: DailyModel? = null


):Parcelable{
    constructor(parcel: Parcel) : this(parcel.readParcelable(DailyModel::class.java.classLoader))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(daily, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ForecastModel> {
        override fun createFromParcel(parcel: Parcel): ForecastModel {
            return ForecastModel(parcel)
        }

        override fun newArray(size: Int): Array<ForecastModel?> {
            return arrayOfNulls(size)
        }
    }

}