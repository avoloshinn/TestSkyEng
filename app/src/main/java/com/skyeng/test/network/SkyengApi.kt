package com.skyeng.test.network

import com.skyeng.test.data.Word
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created on 11.07.2020 by Andrey Voloshin.
 */
interface SkyengApi {

    @GET("words/search")
    fun searchTranslate(@QueryMap params: HashMap<String, Any>): Single<MutableList<Word>>
}