package com.creatis.audition.data.playtrack.playtracklist


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Urlparams(
    @Json(name = "{tracktitle}")
    val tracktitle: String,
    @Json(name = "{trackartist}")
    val trackartist: String
)