package com.skyeng.test.interactors

import com.skyeng.test.data.Word
import com.skyeng.test.network.SkyengApi
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created on 11.07.2020 by Andrey Voloshin.
 */
interface SearchInteractor {
    fun searchTranslate(params: HashMap<String, Any>): Single<MutableList<Word>>
}

class SearchInteractorImpl @Inject constructor(
    private val api: SkyengApi
): SearchInteractor {

    override fun searchTranslate(params: HashMap<String, Any>): Single<MutableList<Word>> {
        return api.searchTranslate(params)
    }
}