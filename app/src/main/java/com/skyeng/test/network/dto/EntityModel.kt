package com.skyeng.test.network.dto

/**
 * Created on 22.07.2020 by Andrey Voloshin.
 */
interface EntityModel<T> {
    fun produceEntity(): T
}