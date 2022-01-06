package com.creatis.audition.data.database.room

import androidx.annotation.Nullable
import androidx.room.Embedded
import androidx.room.Relation
import com.creatis.audition.data.database.room.models.*

/*
* Relations for the main class
* */
data class TrackAndShare (
    @Embedded
    var track: TrackModel,
    @Relation(
        parentColumn = "share_id",
        entityColumn = "track_id"
    )
    var share: ShareModel
)

data class TrackAndImages (
    @Embedded
    var track: TrackModel,
    @Relation(
        parentColumn = "image_id",
        entityColumn = "track_id"
    )
    var images: ImagesModel?=null
)

data class TrackAndProperties (
    @Embedded
    var track: TrackModel,
    @Relation(
        parentColumn = "share_id",
        entityColumn = "track_id"
    )
    @Nullable
    var share: ShareModel?=null,
    @Relation(
        parentColumn = "image_id",
        entityColumn = "track_id"
    )
    @Nullable
    var images: ImagesModel?=null
)
