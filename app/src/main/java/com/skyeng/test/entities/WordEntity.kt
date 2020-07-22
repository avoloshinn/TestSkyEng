package com.skyeng.test.entities

import android.os.Parcelable
import com.skyeng.test.models.Word
import com.skyeng.test.ui.list.ListItemType
import com.skyeng.test.ui.list.ViewType
import kotlinx.android.parcel.Parcelize

/**
 * Created on 12.07.2020 by Andrey Voloshin.
 */
@Parcelize
data class WordEntity(
    override val id: Int,
    override val text: String?,
    override val meanings: List<MeaningEntity>?
) : Word, ViewType, Parcelable {

    override fun getViewType(): Int = ListItemType.WORD.ordinal
}