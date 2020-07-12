package com.skyeng.test.data

import android.os.Parcel
import android.os.Parcelable
import com.skyeng.test.ui.list.ListItemType
import com.skyeng.test.ui.list.ViewType

/**
 * Created on 12.07.2020 by Andrey Voloshin.
 */
data class Word(
    val id: Int,
    val text: String,
    val meanings: List<Meaning>
) : ViewType, Parcelable {

    override fun getViewType(): Int = ListItemType.WORD.ordinal

    constructor(parcel: Parcel) : this(
        parcel.readInt() ?: 0,
        parcel.readString() ?: "",
        listOf<Meaning>().apply {
            parcel.readList(this, Meaning::class.java.classLoader)
        }
    ) {
    }

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeInt(id)
        parcel?.writeString(text)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Word> {
        override fun createFromParcel(parcel: Parcel): Word {
            return Word(parcel)
        }

        override fun newArray(size: Int): Array<Word?> {
            return arrayOfNulls(size)
        }
    }
}