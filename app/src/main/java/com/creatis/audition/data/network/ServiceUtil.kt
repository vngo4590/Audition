package com.creatis.audition.data.network

import com.creatis.audition.data.database.ShazamSongsApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.*
import java.util.concurrent.TimeUnit
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory


const val BASE_URL = "https://shazam.p.rapidapi.com/"

/**
 * To add header to every single request
 * */
private val headerInterceptor = HeaderInterceptor()

/**
 * Single Client to be used across Http Calls
 * */
private val clientBuilder: OkHttpClient.Builder = OkHttpClient().newBuilder()
    .connectTimeout(10, TimeUnit.SECONDS)
    .readTimeout(30, TimeUnit.SECONDS)
    .addInterceptor(headerInterceptor)

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Configure retrofit to parse JSON and use coroutines
 * */
private val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(clientBuilder.build()).build()

/**
 * Centralized class to fetch Data from the API
 * */
class ServiceUtil {
    companion object {
        /**
         * This method will accept a map of params and then returns the build of HTTP with requests
         * */
        @JvmStatic
        fun getUrl(url: String, params: Map<String, String>): HttpUrl {

            val httpBuilder = HttpUrl.parse(url)!!.newBuilder()
            for (param in params) {
                httpBuilder.addQueryParameter(param.key, param.value);
            }
            return httpBuilder.build()
        }

        /**
         * Run Service Build and get the tracks from the API
         * */
        @JvmStatic
        fun serviceApiCreate(): ShazamSongsApiService {
            return retrofit.create(ShazamSongsApiService::class.java)
        }
    }
}

