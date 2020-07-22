package com.skyeng.test.network.dto

import com.skyeng.test.entities.WordEntity
import com.skyeng.test.models.Word

/**
 * Created on 22.07.2020 by Andrey Voloshin.
 */
class WordModel(
    override val id: Int,
    override val text: String?,
    override val meanings: List<MeaningModel>?
) : Word, EntityModel<WordEntity> {

    override fun produceEntity() = WordEntity(
        id,
        text ?: "",
        meanings?.map { it.produceEntity() } ?: listOf()
    )
}