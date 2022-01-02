package com.creatis.audition.data.database.room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(
    tableName = "Image",
    foreignKeys = [ForeignKey(
        entity = TrackModel::class,
        parentColumns = arrayOf("track_id"),
        childColumns = arrayOf("track_id"),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE,
    )]
)
data class ImageModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "image_id")
    val imageId : Long = 0L,
    @ColumnInfo(name = "track_id", defaultValue = "NULL")
    var trackId: String? = null,
    @ColumnInfo(name = "background", defaultValue = "NULL")
    val background: String?,
    @ColumnInfo(name = "cover_art", defaultValue = "NULL")
    val coverArt: String?,
    @ColumnInfo(name = "cover_art_hq", defaultValue = "NULL")
    val coverArtHq: String?,
    @ColumnInfo(name = "joe_color", defaultValue = "NULL")
    val joeColor: String?,
    @ColumnInfo(name = "overflow", defaultValue = "NULL")
    val overflow: String?,
    @ColumnInfo(name = "default", defaultValue = "NULL")
    val default: String?
)
