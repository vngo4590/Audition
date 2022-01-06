package com.creatis.audition.data.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.creatis.audition.data.database.room.TrackAndImages
import com.creatis.audition.data.database.room.TrackAndProperties
import com.creatis.audition.data.database.room.TrackAndShare
import com.creatis.audition.data.database.room.models.ImagesModel
import com.creatis.audition.data.database.room.models.ShareModel

@Dao
interface TrackRelationDao : TrackDao, ImageDao, ShareDao {
    /*
    * Get Relational Data
    * */
    @Transaction
    @Query("SELECT * FROM Track")
    fun getTrackAndImage(): LiveData<List<TrackAndImages>>

    @Transaction
    @Query("SELECT * FROM Track")
    fun getTrackAndShare(): LiveData<List<TrackAndShare>>

    @Transaction
    @Query("SELECT * FROM Track")
    fun getTrackAndProperties(): LiveData<List<TrackAndProperties>>

    /*
    * Insert relational Data
    * */
    @Transaction
    suspend fun insertTrackAndShare(trackId: String, shareModel: ShareModel?): Long? {
        if (shareModel != null) {
            shareModel.trackId = trackId
            return insertShare(shareModel)
        }
        return null
    }

    @Transaction
    suspend fun insertTrackAndImage(trackId: String, images: ImagesModel?): Long? {
        if (images != null) {
            images.trackId = trackId
            return insertImage(images)
        }
        return null
    }

    @Transaction
    suspend fun insertTrackAndProperties(trackAndProperties: TrackAndProperties) {
        val trackId = trackAndProperties.track.trackId
        trackAndProperties.track.imageId = trackId
        trackAndProperties.track.shareId = trackId
        insertTrack(trackAndProperties.track)
        insertTrackAndImage(trackId, trackAndProperties.images)
        insertTrackAndShare(trackId, trackAndProperties.share)
    }

    @Transaction
    suspend fun insertTrackAndPropertiesList(trackAndPropertiesList: List<TrackAndProperties>) {
        trackAndPropertiesList.forEach {
            insertTrackAndProperties(it)
        }
    }
}