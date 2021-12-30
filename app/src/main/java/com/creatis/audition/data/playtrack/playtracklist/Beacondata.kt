package com.creatis.audition.data.playtrack.playtracklist


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Beacondata(
    @Json(name = "type")
    val type: String?=null,
    @Json(name = "providername")
    val providername: String?=null,
    @Json(name = "lyricsid")
    val lyricsid: String?=null,
    @Json(name = "commontrackid")
    val commontrackid: String?=null
)