package com.qrolic.blueair.model.home

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class CoModel (

    @SerializedName("v")
    @Expose
    val v: Double? = null

):Parcelable{
    constructor(parcel: Parcel) : this(parcel.readValue(Double::class.java.classLoader) as? Double)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(v)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CoModel> {
        override fun createFromParcel(parcel: Parcel): CoModel {
            return CoModel(parcel)
        }

        override fun newArray(size: Int): Array<CoModel?> {
            return arrayOfNulls(size)
        }
    }

}