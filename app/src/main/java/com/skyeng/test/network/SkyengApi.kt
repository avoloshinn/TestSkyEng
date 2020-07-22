package com.skyeng.test.network

import com.skyeng.test.entities.WordEntity
import com.skyeng.test.network.dto.WordModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created on 11.07.2020 by Andrey Voloshin.
 */
interface SkyengApi {

    @GET("words/search")
    fun searchTranslate(@QueryMap params: HashMap<String, Any>): Single<List<WordModel>>
}