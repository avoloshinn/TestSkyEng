package com.skyeng.test.data

import android.os.Parcel
import android.os.Parcelable

/**
 * Created on 12.07.2020 by Andrey Voloshin.
 */
data class Meaning(
    val id: Int,
    val partOfSpeechCode: String,
    val translation: Translation?,
    val previewUrl: String,
    val imageUrl: String,
    val transcription: String,
    val soundUrl: String
) : Parcelable {

    public fun formatImageUrl() = "https:$imageUrl"

    public fun formatSoundUrl() = "https:$soundUrl"

    constructor(parcel: Parcel) : this(
        parcel.readInt() ?: 0,
        parcel.readString() ?: "",
        parcel.readParcelable(Translation::class.java.classLoader),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(partOfSpeechCode)
        parcel.writeString(previewUrl)
        parcel.writeString(imageUrl)
        parcel.writeString(transcription)
        parcel.writeString(soundUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Meaning> {
        override fun createFromParcel(parcel: Parcel): Meaning {
            return Meaning(parcel)
        }

        override fun newArray(size: Int): Array<Meaning?> {
            return arrayOfNulls(size)
        }
    }
}