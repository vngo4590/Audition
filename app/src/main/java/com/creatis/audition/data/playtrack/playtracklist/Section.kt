package com.creatis.audition.data.playtrack.playtracklist


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Section(
    @Json(name = "type")
    val type: String?=null,
    @Json(name = "metapages")
    val metapages: List<Metapage>?=null,
    @Json(name = "tabname")
    val tabname: String?=null,
    @Json(name = "metadata")
    val metadata: List<Metadata>?=null,
    @Json(name = "text")
    val text: List<String>?=null,
    @Json(name = "footer")
    val footer: String?=null,
    @Json(name = "beacondata")
    val beacondata: Beacondata?=null,
    @Json(name = "youtubeurl")
    val youtubeurl: Youtubeurl?=null,
    @Json(name = "avatar")
    val avatar: String?=null,
    @Json(name = "id")
    val id: String?=null,
    @Json(name = "name")
    val name: String?=null,
    @Json(name = "verified")
    val verified: Boolean?=null,
    @Json(name = "actions")
    val actions: List<Action>?=null
)