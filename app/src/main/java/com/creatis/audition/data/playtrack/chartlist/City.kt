package com.creatis.audition.data.playtrack.chartlist


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class City(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "countryid")
    val countryid: String,
    @Json(name = "listid")
    val listid: String
)