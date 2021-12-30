package com.creatis.audition.data.playtrack.playtracklist


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Provider(
    @Json(name = "caption")
    val caption: String,
    @Json(name = "images")
    val images: Images,
    @Json(name = "actions")
    val actions: List<Action>,
    @Json(name = "type")
    val type: String
)