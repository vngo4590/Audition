package com.creatis.audition.data.playtrack.playtracklist


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Image(
    @Json(name = "dimensions")
    val dimensions: Dimensions,
    @Json(name = "url")
    val url: String
)