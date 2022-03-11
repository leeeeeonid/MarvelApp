package com.lealpy.marvelapp.data.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

object ApiUtils {

    private const val PRIVATE_API_KEY = "31c2943bf7230fa68ffe8819e56df26ed8716a27"
    const val API_KEY = "fa87c4045ab074db4dd725920803d9a4"

    fun getTimeStamp(): String {
        return Timestamp(System.currentTimeMillis()).time.toString()
    }

    fun getApiHash(): String {
        val input = "${getTimeStamp()}${PRIVATE_API_KEY}${API_KEY}"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

}
