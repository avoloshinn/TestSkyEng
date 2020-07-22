package com.skyeng.test.interactors

import com.skyeng.test.entities.WordEntity
import com.skyeng.test.network.SkyengApi
import com.skyeng.test.network.dto.WordModel
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created on 11.07.2020 by Andrey Voloshin.
 */
interface SearchInteractor {
    fun searchTranslate(params: HashMap<String, Any>): Observable<List<WordModel>>
}

class SearchInteractorImpl @Inject constructor(
    private val api: SkyengApi
): SearchInteractor {

    override fun searchTranslate(params: HashMap<String, Any>): Observable<List<WordModel>> {
        return api.searchTranslate(params)
    }
}