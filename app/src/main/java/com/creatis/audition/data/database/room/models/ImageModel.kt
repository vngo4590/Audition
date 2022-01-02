package com.creatis.audition.data.database.room.models

import androidx.room.*
import com.squareup.moshi.Json

@Entity(
    tableName = "Image",
    foreignKeys = [ForeignKey(
        entity = TrackModel::class,
        parentColumns = arrayOf("track_id"),
        childColumns = arrayOf("track_id"),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE,
    )],
    indices = [
        Index(value = arrayOf("track_id"), unique = true),
        Index(value = arrayOf("image_id"), unique = true),
        Index(value = arrayOf("track_id", "image_id"), unique = true),
    ]
)
data class ImageModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "image_id")
    var imageId : Long = 0L,
    @ColumnInfo(name = "track_id", defaultValue = "NULL")
    var trackId: String? = null,
    @ColumnInfo(name = "background", defaultValue = "NULL")
    var background: String?= null,
    @ColumnInfo(name = "cover_art", defaultValue = "NULL")
    var coverArt: String?= null,
    @ColumnInfo(name = "cover_art_hq", defaultValue = "NULL")
    var coverArtHq: String?= null,
    @ColumnInfo(name = "joe_color", defaultValue = "NULL")
    var joeColor: String?= null,
    @ColumnInfo(name = "overflow", defaultValue = "NULL")
    var overflow: String?= null,
    @ColumnInfo(name = "default", defaultValue = "NULL")
    var default: String?= null
)
