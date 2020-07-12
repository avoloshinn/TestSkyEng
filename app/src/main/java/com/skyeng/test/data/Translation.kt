package com.skyeng.test.data

import android.os.Parcel
import android.os.Parcelable

/**
 * Created on 12.07.2020 by Andrey Voloshin.
 */
data class Translation(
    val text: String,
    val note: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(text)
        parcel.writeString(note)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Translation> {
        override fun createFromParcel(parcel: Parcel): Translation {
            return Translation(parcel)
        }

        override fun newArray(size: Int): Array<Translation?> {
            return arrayOfNulls(size)
        }
    }
}