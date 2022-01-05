package com.creatis.audition.data.database.room.dao

import androidx.room.Dao
import androidx.room.Transaction
import com.creatis.audition.data.database.room.TrackAndProperties
import com.creatis.audition.data.database.room.models.ImagesModel
import com.creatis.audition.data.database.room.models.ShareModel

@Dao
interface RelationDao : TrackDao, ImageDao, ShareDao {
    /*
    * Insert relational Data
    * */
    @Transaction
    fun insertTrackAndShare(trackId: String, shareModel: ShareModel?) {
        shareModel?.trackId = trackId
        shareModel?.let { insertShare(it) }
    }

    @Transaction
    fun insertTrackAndImage(trackId: String, images: ImagesModel?) {
        images?.trackId = trackId
        images?.let { insertImage(it) }
    }

    @Transaction
    fun insertTrackAndProperties(trackAndProperties: TrackAndProperties) {
        val trackId = trackAndProperties.track.trackId
        insertTrack(trackAndProperties.track)
        insertTrackAndShare(trackId, trackAndProperties.share)
        insertTrackAndImage(trackId, trackAndProperties.images)
    }
}