package com.qrolic.blueair.model.home

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class HomeResponseModel (
    @SerializedName("status")
    @Expose
    val status: String? = null,

    @SerializedName("data")
    @Expose
    val data: HomeModel? = null,

//    @SerializedName("data")
//    @Expose
//    val data2: String? = null

):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(HomeModel::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(status)
        parcel.writeParcelable(data, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HomeResponseModel> {
        override fun createFromParcel(parcel: Parcel): HomeResponseModel {
            return HomeResponseModel(parcel)
        }

        override fun newArray(size: Int): Array<HomeResponseModel?> {
            return arrayOfNulls(size)
        }
    }


}

