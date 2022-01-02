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
    val track: TrackModel,
    @Relation(
        parentColumn = "share_id",
        entityColumn = "share_id"
    )
    val share: ShareModel
)

data class TrackAndImage (
    @Embedded
    val track: TrackModel,
    @Relation(
        parentColumn = "image_id",
        entityColumn = "image_id"
    )
    val image: ImageModel
)


