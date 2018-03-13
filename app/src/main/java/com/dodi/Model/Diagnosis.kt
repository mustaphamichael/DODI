package com.dodi.Model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created by mmustapha on 12/21/17.
 */
data class Diagnosis (
        var disease: String = "",
        var symptom: Array<String> = arrayOf(),
        var treatment: String = ""
): Model(), Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.createStringArray(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(disease)
        parcel.writeStringArray(symptom)
        parcel.writeString(treatment)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Diagnosis

        if (disease != other.disease) return false
        if (!Arrays.equals(symptom, other.symptom)) return false
        if (treatment != other.treatment) return false

        return true
    }

    override fun hashCode(): Int {
        var result = disease.hashCode()
        result = 31 * result + Arrays.hashCode(symptom)
        result = 31 * result + treatment.hashCode()
        return result
    }

    companion object CREATOR : Parcelable.Creator<Diagnosis> {
        override fun createFromParcel(parcel: Parcel): Diagnosis {
            return Diagnosis(parcel)
        }

        override fun newArray(size: Int): Array<Diagnosis?> {
            return arrayOfNulls(size)
        }
    }

}