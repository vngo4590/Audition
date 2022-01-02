package com.creatis.audition.data.database.room.models

import androidx.room.*

@Entity(
    tableName = "Share",
    foreignKeys = [
        ForeignKey(
            entity = TrackModel::class,
            parentColumns = arrayOf("track_id"),
            childColumns = arrayOf("track_id"),
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index(value = arrayOf("track_id"), unique = true),
        Index(value = arrayOf("share_id"), unique = true),
        Index(value = arrayOf("track_id", "share_id"), unique = true),
    ]
)
data class ShareModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "share_id")
    var shareId: Long = 0L,
    @ColumnInfo(name = "track_id", defaultValue = "NULL")
    var trackId: String? = null,
    @ColumnInfo(name = "subject")
    val subject: String,
    @ColumnInfo(name = "text")
    val text: String,
    @ColumnInfo(name = "href")
    val href: String,
    @ColumnInfo(name = "image", defaultValue = "NULL")
    var image: String?=null,
    @ColumnInfo(name = "twitter")
    val twitter: String? = null,
    @ColumnInfo(name = "html")
    val html: String,
    @ColumnInfo(name = "avatar")
    val avatar: String? = null,
    @ColumnInfo(name = "snapchat")
    val snapchat: String? = null
)