package com.lealpy.marvelapp.data.api

import com.lealpy.marvelapp.data.models.CharactersResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

interface CharactersApi {

    @GET("/v1/public/characters")
    fun getCharacters(
        @Query("apikey") apikey: String = API_KEY,
        @Query("ts") ts: String = timeStamp,
        @Query("hash") hash: String = hash(),
        @Query("offset") offset: String = "20"
    ): Single<CharactersResponse>

    companion object {
        private val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()
        private const val API_KEY = "fa87c4045ab074db4dd725920803d9a4"
        private const val PRIVATE_KEY = "31c2943bf7230fa68ffe8819e56df26ed8716a27"
        private const val limit = "20"
        private fun hash(): String {
            val input = "$timeStamp$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32,'0')
        }
    }

}