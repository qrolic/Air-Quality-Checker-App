package com.qrolic.blueair.model.home

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class O31Model (

    @SerializedName("avg")
    @Expose
    val avg: Int? = null,

    @SerializedName("day")
    @Expose
    val day: String? = null,

    @SerializedName("max")
    @Expose
    val max: Int? = null,

    @SerializedName("min")
    @Expose
    val min: Int? = null,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(avg)
        parcel.writeString(day)
        parcel.writeValue(max)
        parcel.writeValue(min)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<O31Model> {
        override fun createFromParcel(parcel: Parcel): O31Model {
            return O31Model(parcel)
        }

        override fun newArray(size: Int): Array<O31Model?> {
            return arrayOfNulls(size)
        }
    }

}