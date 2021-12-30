package com.creatis.audition.data.network

import okhttp3.Interceptor
import okhttp3.Response



/**
 * Uses interceptor to build up response with Headers
 * */
class HeaderInterceptor : Interceptor {
    companion object {
        const val API_HOST = "shazam.p.rapidapi.com"
        const val API_KEY = "e2f55d2e12msh6d886fa1aff9d4dp10da0bjsnfe87c160ae51"
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