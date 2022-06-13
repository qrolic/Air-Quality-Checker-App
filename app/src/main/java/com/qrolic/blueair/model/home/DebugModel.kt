package com.qrolic.blueair.model.home

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class DebugModel (

    @SerializedName("sync")
    @Expose
    private val sync: String? = null

    ):Parcelable{
    constructor(parcel: Parcel) : this(parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(sync)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DebugModel> {
        override fun createFromParcel(parcel: Parcel): DebugModel {
            return DebugModel(parcel)
        }

        override fun newArray(size: Int): Array<DebugModel?> {
            return arrayOfNulls(size)
        }
    }

}