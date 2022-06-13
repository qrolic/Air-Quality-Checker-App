package com.qrolic.blueair.model.home

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class CityModel (

    @SerializedName("geo")
    @Expose
    val geo: List<Double>? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("url")
    @Expose
    val url: String? = null,

    @SerializedName("location")
    @Expose
    val location: String? = null,
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.createDoubleList(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDoubleList(geo!!)
        parcel.writeString(name)
        parcel.writeString(url)
        parcel.writeString(location)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CityModel> {
        override fun createFromParcel(parcel: Parcel): CityModel {
            return CityModel(parcel)
        }

        override fun newArray(size: Int): Array<CityModel?> {
            return arrayOfNulls(size)
        }
    }


}

fun Parcel.writeDoubleList(input:List<Double>) {
    writeInt(input.size) // Save number of elements.
    input.forEach(this::writeDouble) // Save each element.
}

private fun Parcel.createDoubleList() : List<Double> {
    val size = readInt()
    val output = ArrayList<Double>(size)
    for (i in 0 until size) {
        output.add(readDouble())
    }
    return output
}
