package com.skyeng.test.entities

import android.os.Parcelable
import com.skyeng.test.models.Meaning
import kotlinx.android.parcel.Parcelize

/**
 * Created on 12.07.2020 by Andrey Voloshin.
 */
@Parcelize
data class MeaningEntity(
    override val id: Int,
    override val partOfSpeechCode: String?,
    override val translation: TranslationEntity?,
    override val previewUrl: String?,
    override val imageUrl: String?,
    override val transcription: String?,
    override val soundUrl: String?
) : Meaning, Parcelable {

    public fun formatImageUrl() = "https:$imageUrl"

    public fun formatSoundUrl() = "https:$soundUrl"
}