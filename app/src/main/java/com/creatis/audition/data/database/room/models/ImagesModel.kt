package com.creatis.audition.data.database.room.models

import androidx.room.*

@Entity(
    tableName = "Images",
    foreignKeys = [ForeignKey(
        entity = TrackModel::class,
        parentColumns = arrayOf("track_id"),
        childColumns = arrayOf("track_id"),
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE,
    )],
    indices = [
        Index(value = arrayOf("track_id"), unique = true)
    ]
)
data class ImagesModel(
    @PrimaryKey
    @ColumnInfo(name = "track_id")
    var trackId: String,
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
    @ColumnInfo(name = "default_value", defaultValue = "NULL")
    var defaultValue: String?= null,
) : ChildRelationModel {
    override fun setParentId(id: String) {
        trackId = id
    }

    override fun getParentId(): String? {
        return trackId
    }
}
