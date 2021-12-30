package com.creatis.audition.data.playtrack.chartlist


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Country(
    @Json(name = "id")
    val id: String,
    @Json(name = "listid")
    val listid: String,
    @Json(name = "momentum_listid")
    val momentumListid: String? = null,
    @Json(name = "name")
    val name: String,
    @Json(name = "cities")
    val cities: List<City>,
    @Json(name = "genres")
    val genres: List<Genre>
)