package com.creatis.audition.data.database.room

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
        entityColumn = "share_id"
    )
    var share: ShareModel
)

data class TrackAndImages (
    @Embedded
    var track: TrackModel,
    @Relation(
        parentColumn = "image_id",
        entityColumn = "image_id"
    )
    var images: ImagesModel
)

data class TrackAndProperties (
    @Embedded
    var track: TrackModel,
    @Relation(
        parentColumn = "share_id",
        entityColumn = "share_id"
    )
    var share: ShareModel,
    @Relation(
        parentColumn = "image_id",
        entityColumn = "image_id"
    )
    var images: ImagesModel?=null
)
