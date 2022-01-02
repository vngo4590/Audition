package com.creatis.audition.data.playtrack.playtracklist


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hub(
    @Json(name = "type")
    val type: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "actions")
    val actions: List<Action>?=null,
    @Json(name = "options")
    val options: List<Option>,
    @Json(name = "providers")
    val providers: List<Provider>?=null,
    @Json(name = "explicit")
    val explicit: Boolean?=false,
    @Json(name = "displayname")
    val displayname: String
)