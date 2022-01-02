package com.creatis.audition.data.database.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Track")
data class TrackModel(
    @PrimaryKey
    @ColumnInfo(name = "track_id")
    val trackId : String,
    @ColumnInfo(name = "layout")
    val layout: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "subtitle")
    val subtitle: String,
    @ColumnInfo(name = "share_id")
    var shareId: Long=0L,
    @ColumnInfo(name = "image_id")
    var imageId: Long=0L,
    @ColumnInfo(name = "url")
    val url: String,
    ) {
}