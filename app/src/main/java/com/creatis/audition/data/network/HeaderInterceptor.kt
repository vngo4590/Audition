package com.creatis.audition.data.network

import okhttp3.Interceptor
import okhttp3.Response



/**
 * Uses interceptor to build up response with Headers
 * */
class HeaderInterceptor : Interceptor {
    companion object {
        const val API_HOST = "shazam.p.rapidapi.com"
        const val API_KEY = "5b97ed904amsh7fc419def9d8186p1bc2c6jsn197bea2f7ffa"
    }
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("x-rapidapi-host", API_HOST)
                .addHeader("x-rapidapi-key", API_KEY)
                .build()
        )
    }
}