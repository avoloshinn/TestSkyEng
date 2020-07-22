package com.skyeng.test.network.dto

import com.skyeng.test.entities.TranslationEntity
import com.skyeng.test.models.Translation

/**
 * Created on 22.07.2020 by Andrey Voloshin.
 */
class TranslationModel(
    override val text: String?,
    override val note: String?
) : Translation, EntityModel<TranslationEntity> {

    override fun produceEntity() = TranslationEntity(text ?: "", note ?: "")
}