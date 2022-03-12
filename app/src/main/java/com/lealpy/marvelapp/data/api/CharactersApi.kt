package com.lealpy.marvelapp.data.api

import com.lealpy.marvelapp.data.models.CharactersResponse
import com.lealpy.marvelapp.data.utils.ApiUtils.API_KEY
import com.lealpy.marvelapp.data.utils.ApiUtils.getApiHash
import com.lealpy.marvelapp.data.utils.ApiUtils.getTimeStamp
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApi {

    @GET("/v1/public/characters")
    fun getCharacters(
        @Query("apikey") apikey: String = API_KEY,
        @Query("ts") ts: String = getTimeStamp(),
        @Query("hash") hash: String = getApiHash(),
        @Query("limit") limit: String,
    ): Single<CharactersResponse>

}
