package com.creatis.audition.data.playtrack.playtracklist


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Images(
    @Json(name = "background")
    val background: String?=null,
    @Json(name = "coverart")
    val coverart: String?=null,
    @Json(name = "coverarthq")
    val coverarthq: String?=null,
    @Json(name = "joecolor")
    val joecolor: String?=null,
    @Json(name = "overflow")
    val overflow: String?=null,
    @Json(name = "default")
    val default: String?=null
)