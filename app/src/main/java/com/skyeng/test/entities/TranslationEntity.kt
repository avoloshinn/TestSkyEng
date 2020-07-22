package com.skyeng.test.entities

import android.os.Parcelable
import com.skyeng.test.models.Translation
import kotlinx.android.parcel.Parcelize

/**
 * Created on 12.07.2020 by Andrey Voloshin.
 */
@Parcelize
data class TranslationEntity(
    override val text: String?,
    override val note: String?
) : Translation, Parcelable