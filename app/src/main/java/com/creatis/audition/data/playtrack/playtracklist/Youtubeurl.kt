package com.creatis.audition.data.playtrack.playtracklist


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Youtubeurl(
    @Json(name = "caption")
    val caption: String,
    @Json(name = "image")
    val image: Image,
    @Json(name = "actions")
    val actions: List<Action>
)