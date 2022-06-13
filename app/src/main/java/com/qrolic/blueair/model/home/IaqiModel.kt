package com.qrolic.blueair.model.home


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class IaqiModel (


    @SerializedName("no2")
    @Expose
    val no2: CoModel? = null,

    @SerializedName("wg")
    @Expose
    val wg: CoModel? = null,




    @SerializedName("co")
    @Expose
    val co: CoModel? = null,

    @SerializedName("dew")
    @Expose
    val dew: CoModel? = null,

    @SerializedName("h")
    @Expose
    val h: CoModel? = null,

    @SerializedName("o3")
    @Expose
    val o3: CoModel? = null,

    @SerializedName("p")
    @Expose
    val p: CoModel? = null,

    @SerializedName("pm10")
    @Expose
    val pm10: CoModel? = null,

    @SerializedName("pm25")
    @Expose
    val pm25: CoModel? = null,

    @SerializedName("so2")
    @Expose
    val so2: CoModel? = null,

    @SerializedName("t")
    @Expose
    val t: CoModel? = null,

    @SerializedName("w")
    @Expose
    val w: CoModel? = null,
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(CoModel::class.java.classLoader),
        parcel.readParcelable(CoModel::class.java.classLoader),
        parcel.readParcelable(CoModel::class.java.classLoader),
        parcel.readParcelable(CoModel::class.java.classLoader),
        parcel.readParcelable(CoModel::class.java.classLoader),
        parcel.readParcelable(CoModel::class.java.classLoader),
        parcel.readParcelable(CoModel::class.java.classLoader),
        parcel.readParcelable(CoModel::class.java.classLoader),
        parcel.readParcelable(CoModel::class.java.classLoader),
        parcel.readParcelable(CoModel::class.java.classLoader),
        parcel.readParcelable(CoModel::class.java.classLoader),
        parcel.readParcelable(CoModel::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(no2, flags)
        parcel.writeParcelable(wg, flags)
        parcel.writeParcelable(co, flags)
        parcel.writeParcelable(dew, flags)
        parcel.writeParcelable(h, flags)
        parcel.writeParcelable(o3, flags)
        parcel.writeParcelable(p, flags)
        parcel.writeParcelable(pm10, flags)
        parcel.writeParcelable(pm25, flags)
        parcel.writeParcelable(so2, flags)
        parcel.writeParcelable(t, flags)
        parcel.writeParcelable(w, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IaqiModel> {
        override fun createFromParcel(parcel: Parcel): IaqiModel {
            return IaqiModel(parcel)
        }

        override fun newArray(size: Int): Array<IaqiModel?> {
            return arrayOfNulls(size)
        }
    }
}