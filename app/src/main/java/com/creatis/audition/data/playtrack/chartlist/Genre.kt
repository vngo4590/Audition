package com.creatis.audition.data.playtrack.chartlist


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Genre(
    @Json(name = "id")
    val id: String,
    @Json(name = "countryid")
    val countryid: String? = null,
    @Json(name = "listid")
    val listid: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "urlPath")
    val urlPath: String,
    @Json(name = "count")
    val count: Int
)