package com.qrolic.blueair.model

import android.os.Parcel
import android.os.Parcelable

data class PollutantModel(
    val name:Int,
    val desc:Int,
    val sortTermEffectList:List<Int>,
    val longTermEffectList:List<Int>,
    val causeList:List<Int>
):Parcelable{


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.createIntList(),
        parcel.createIntList(),
        parcel.createIntList()
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(name)
        parcel.writeInt(desc)
        parcel.writeIntList(sortTermEffectList)
        parcel.writeIntList(longTermEffectList)
        parcel.writeIntList(causeList)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PollutantModel> {
        override fun createFromParcel(parcel: Parcel): PollutantModel {
            return PollutantModel(parcel)
        }

        override fun newArray(size: Int): Array<PollutantModel?> {
            return arrayOfNulls(size)
        }
    }



}

fun Parcel.writeIntList(input:List<Int>) {
    writeInt(input.size) // Save number of elements.
    input.forEach(this::writeInt) // Save each element.
}

private fun Parcel.createIntList() : List<Int> {
    val size = readInt()
    val output = ArrayList<Int>(size)
    for (i in 0 until size) {
        output.add(readInt())
    }
    return output
}