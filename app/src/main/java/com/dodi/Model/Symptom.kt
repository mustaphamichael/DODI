package com.dodi.Model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by mmustapha on 12/16/17.
 */
data class Symptom(
        val id: String = "",
        val symptomName: String = "",
        var isChecked: Boolean = false

) : Model(), Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(symptomName)
        parcel.writeByte(if (isChecked) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Symptom> {
        override fun createFromParcel(parcel: Parcel): Symptom {
            return Symptom(parcel)
        }

        override fun newArray(size: Int): Array<Symptom?> {
            return arrayOfNulls(size)
        }
    }
}