package com.creatis.audition.data.playtrack.chartlist


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Global(
    @Json(name = "genres")
    val genres: List<Genre>
)