package com.skyeng.test.models

import com.skyeng.test.entities.TranslationEntity

/**
 * Created on 22.07.2020 by Andrey Voloshin.
 */
interface Meaning {
    val id: Int
    val partOfSpeechCode: String?
    val translation: Translation?
    val previewUrl: String?
    val imageUrl: String?
    val transcription: String?
    val soundUrl: String?
}