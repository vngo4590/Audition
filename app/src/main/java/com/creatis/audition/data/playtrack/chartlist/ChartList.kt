package com.creatis.audition.data.playtrack.chartlist


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChartList(
    @Json(name = "countries")
    val countries: List<Country>,
    @Json(name = "global")
    val global: Global
)