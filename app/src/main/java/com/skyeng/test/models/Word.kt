package com.skyeng.test.models

import com.skyeng.test.entities.MeaningEntity

/**
 * Created on 22.07.2020 by Andrey Voloshin.
 */
interface Word {
    val id: Int
    val text: String?
    val meanings: List<Meaning>?
}