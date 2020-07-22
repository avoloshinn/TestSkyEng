package com.skyeng.test.network.dto

import com.skyeng.test.entities.MeaningEntity
import com.skyeng.test.models.Meaning

/**
 * Created on 22.07.2020 by Andrey Voloshin.
 */
class MeaningModel(
    override val id: Int,
    override val partOfSpeechCode: String?,
    override val translation: TranslationModel?,
    override val previewUrl: String?,
    override val imageUrl: String?,
    override val transcription: String?,
    override val soundUrl: String?
) : Meaning, EntityModel<MeaningEntity> {

    override fun produceEntity() = MeaningEntity(
        id,
        partOfSpeechCode ?: "",
        translation?.produceEntity(),
        previewUrl ?: "",
        imageUrl ?: "",
        transcription ?: "",
        soundUrl ?: ""
    )
}