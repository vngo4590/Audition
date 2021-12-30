package com.creatis.audition.data.playtrack.playtracklist


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Metadata(
    @Json(name = "title")
    val title: String,
    @Json(name = "text")
    val text: String
)