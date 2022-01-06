package com.creatis.audition.data.database.room.models

import androidx.annotation.Nullable
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
    @ColumnInfo(name = "share_id", defaultValue = "NULL")
    @Nullable
    var shareId: String?=null,
    @ColumnInfo(name = "image_id", defaultValue = "NULL")
    @Nullable
    var imageId: String?=null,
    @ColumnInfo(name = "url")
    val url: String,
    ) {
}