package com.creatis.audition.data.playtrack.playtracklist


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Option(
    @Json(name = "caption")
    val caption: String,
    @Json(name = "actions")
    val actions: List<Action>,
    @Json(name = "beacondata")
    val beacondata: Beacondata,
    @Json(name = "image")
    val image: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "listcaption")
    val listcaption: String,
    @Json(name = "overflowimage")
    val overflowimage: String,
    @Json(name = "colouroverflowimage")
    val colouroverflowimage: Boolean,
    @Json(name = "providername")
    val providername: String
)