package com.creatis.audition.data.playtrack.playtracklist


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlayTracks(
    @Json(name = "tracks")
    val tracks: List<Track>?=null
)