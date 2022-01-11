package com.creatis.audition.data.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.creatis.audition.data.database.room.TrackAndImages
import com.creatis.audition.data.database.room.TrackAndProperties
import com.creatis.audition.data.database.room.TrackAndShare
import com.creatis.audition.data.database.room.TrackDatabase
import com.creatis.audition.data.database.room.models.ImagesModel
import com.creatis.audition.data.database.room.models.ShareModel
import kotlinx.coroutines.*

@Dao
interface TrackRelationDao {
    /*
    * Get Relational Data
    * */
    @Transaction
    @Query("SELECT * FROM Track")
    fun getTrackAndImages(): List<TrackAndImages>

    @Transaction
    @Query("SELECT * FROM Track")
    fun getTrackAndShare():
            List<TrackAndShare>

    @Transaction
    @Query("SELECT * FROM Track")
    fun getTrackAndProperties(): LiveData<List<TrackAndProperties>>

    /*
    * Get Track Share By Id
    * */
    @Transaction
    @Query("SELECT * FROM Track WHERE track_id = :trackId")
    fun getTrackAndShareById(trackId: String): TrackAndShare?

    /*
    * Get Track Image By Id
    * */
    @Transaction
    @Query("SELECT * FROM Track WHERE track_id = :trackId")
    fun getTrackAndImagesById(trackId: String): TrackAndImages?

    /*
    * Insert relational Data
    * */
    @Transaction
    suspend fun insertTrackAndShare(
        trackDatabase: TrackDatabase,
        trackId: String,
        shareModel: ShareModel?
    ): Long? {
        if (shareModel != null) {
            shareModel.trackId = trackId
            return trackDatabase.shareDao.insertShare(shareModel)
        }
        return null
    }

    @Transaction
    suspend fun insertTrackAndImage(
        trackDatabase: TrackDatabase,
        trackId: String,
        images: ImagesModel?
    ): Long? {
        if (images != null) {
            images.trackId = trackId
            return trackDatabase.imageDao.insertImage(images)
        }
        return null
    }

    @Transaction
    suspend fun insertTrackAndProperties(
        trackDatabase: TrackDatabase,
        trackAndProperties: TrackAndProperties
    ) {
        coroutineScope {
            val trackId = trackAndProperties.track.trackId
            trackAndProperties.track.imageId = trackId
            trackAndProperties.track.shareId = trackId

            val deferred = async {
                trackDatabase.trackDao.insertTrack(trackAndProperties.track)
            }
            deferred.await()
            val insertChildProperties: List<Deferred<Long?>> =
                listOf(
                    async {
                        insertTrackAndShare(trackDatabase, trackId, trackAndProperties.share)
                    },
                    async {
                        insertTrackAndImage(trackDatabase, trackId, trackAndProperties.images)
                    }
                )
            insertChildProperties.awaitAll()
        }

    }

    @Transaction
    suspend fun insertTrackAndPropertiesList(
        trackDatabase: TrackDatabase,
        trackAndPropertiesList: List<TrackAndProperties>
    ) = coroutineScope {
        val deferred: List<Deferred<Unit>> = trackAndPropertiesList.map {
            async {
                insertTrackAndProperties(trackDatabase, it)
            }
        }
        deferred.awaitAll()
    }
}