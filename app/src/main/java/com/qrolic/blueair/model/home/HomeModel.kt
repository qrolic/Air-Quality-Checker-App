package com.qrolic.blueair.model.home

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class HomeModel(

    @SerializedName("aqi")
    @Expose
    val aqi: Int? = null,

    @SerializedName("idx")
    @Expose
    val idx: Int? = null,

    @SerializedName("attributions")
    @Expose
    val attributions: List<AttributionModel>? = null,

    @SerializedName("city")
    @Expose
    val city: CityModel? = null,

    @SerializedName("dominentpol")
    @Expose
    val dominentpol: String? = null,

    @SerializedName("iaqi")
    @Expose
    val iaqi: IaqiModel? = null,

    @SerializedName("time")
    @Expose
    val time: HomeTimeModel? = null,

    @SerializedName("forecast")
    @Expose
    val forecast: ForecastModel? = null,

    @SerializedName("debug")
    @Expose
    val debug: DebugModel? = null,

):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.createTypedArrayList(AttributionModel),
        parcel.readParcelable(CityModel::class.java.classLoader),
        parcel.readString(),
        parcel.readParcelable(IaqiModel::class.java.classLoader),
        parcel.readParcelable(HomeTimeModel::class.java.classLoader),
        parcel.readParcelable(ForecastModel::class.java.classLoader),
        parcel.readParcelable(DebugModel::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(aqi)
        parcel.writeValue(idx)
        parcel.writeTypedList(attributions)
        parcel.writeParcelable(city, flags)
        parcel.writeString(dominentpol)
        parcel.writeParcelable(iaqi, flags)
        parcel.writeParcelable(time, flags)
        parcel.writeParcelable(forecast, flags)
        parcel.writeParcelable(debug, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HomeModel> {
        override fun createFromParcel(parcel: Parcel): HomeModel {
            return HomeModel(parcel)
        }

        override fun newArray(size: Int): Array<HomeModel?> {
            return arrayOfNulls(size)
        }
    }


}