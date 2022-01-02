package com.creatis.audition.data.playtrack.playtracklist


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Share(
    @Json(name = "subject")
    val subject: String,
    @Json(name = "text")
    val text: String,
    @Json(name = "href")
    val href: String,
    @Json(name = "image")
    val image: String?=null,
    @Json(name = "twitter")
    val twitter: String?=null,
    @Json(name = "html")
    val html: String,
    @Json(name = "avatar")
    val avatar: String?=null,
    @Json(name = "snapchat")
    val snapchat: String?=null
)