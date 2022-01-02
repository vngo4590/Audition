package com.creatis.audition.data.database.room

import androidx.room.Embedded
import androidx.room.Relation
import com.creatis.audition.data.database.room.models.*
import com.creatis.audition.data.playtrack.playtracklist.Track

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

data class TrackAndImage (
    @Embedded
    var track: TrackModel,
    @Relation(
        parentColumn = "image_id",
        entityColumn = "image_id"
    )
    var image: ImageModel
)


