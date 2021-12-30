package com.creatis.audition.data.playtrack.playtracklist


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Action(
    @Json(name = "name")
    val name: String?=null,
    @Json(name = "type")
    val type: String?=null,
    @Json(name = "id")
    val id: String?=null,
    @Json(name = "share")
    val share: Share?=null,
    @Json(name = "uri")
    val uri: String?=null
)