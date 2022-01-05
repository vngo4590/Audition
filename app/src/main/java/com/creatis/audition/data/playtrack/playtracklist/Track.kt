package com.creatis.audition.data.playtrack.playtracklist


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Track(
    @Json(name = "layout")
    val layout: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "key")
    val key: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "subtitle")
    val subtitle: String,
    @Json(name = "share")
    val share: Share,
    @Json(name = "images")
    val images: Images?=null,
    @Json(name = "hub")
    val hub: Hub?,
    @Json(name = "artists")
    val artists: List<Artist>?=null,
    @Json(name = "url")
    val url: String,
    @Json(name = "isrc")
    val isrc: String?=null,
    @Json(name = "genres")
    val genres: Genres?=null,
    @Json(name = "urlparams")
    val urlparams: Urlparams?=null,
    @Json(name = "myshazam")
    val myshazam: Myshazam? = null,
    @Json(name = "albumadamid")
    val albumadamid: String?=null,
    @Json(name = "sections")
    val sections: List<Section>?=null
)