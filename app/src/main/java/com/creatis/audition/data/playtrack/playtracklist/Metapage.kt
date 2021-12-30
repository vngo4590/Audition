package com.creatis.audition.data.playtrack.playtracklist


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Metapage(
    @Json(name = "image")
    val image: String,
    @Json(name = "caption")
    val caption: String
)