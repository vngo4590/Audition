package com.creatis.audition.data.database.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.creatis.audition.data.database.room.TrackAndProperties
import com.creatis.audition.data.database.room.models.ImagesModel
import com.creatis.audition.data.database.room.models.ShareModel
import com.creatis.audition.data.database.room.models.TrackModel

@Dao
interface RelationDao : TrackDao, ImageDao, ShareDao {
    /*
    * Insert relational Data
    * */
    @Transaction
    fun insertTrackAndShare(track: TrackModel, shareModel: ShareModel): TrackModel {
        val trackDb = getTrackByTrackId(track.trackId) ?: track
        shareModel.trackId = trackDb.trackId
        val shareRowId = insertShare(shareModel)
        val shareDb = getShareByRowId(shareRowId).value
        if (shareDb != null) {
            trackDb.shareId = shareDb.shareId
        }
        insertTrack(trackDb)
        return trackDb
    }

    @Transaction
    fun insertTrackAndImage(track: TrackModel, images: ImagesModel?): TrackModel {
        val trackDb = getTrackByTrackId(track.trackId) ?: track
        if (images!=null) {
            images.trackId = trackDb.trackId
            val imgRowId = insertImage(images)
            val imgDb = getImageByRowId(imgRowId)
            if (imgDb != null) {
                trackDb.imageId = imgDb.imageId
            }
        }
        insertTrack(trackDb)
        return trackDb
    }

    @Transaction
    fun insertTrackAndProperties (trackAndProperties: TrackAndProperties): TrackModel {
        val trackDb = insertTrackAndShare(trackAndProperties.track, trackAndProperties.share)
        return insertTrackAndImage(trackDb, trackAndProperties.images)
    }
}