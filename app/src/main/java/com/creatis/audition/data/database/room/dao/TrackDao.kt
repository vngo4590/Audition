package com.creatis.audition.data.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.creatis.audition.data.database.room.TrackAndImages
import com.creatis.audition.data.database.room.TrackAndShare
import com.creatis.audition.data.database.room.models.ImagesModel
import com.creatis.audition.data.database.room.models.ShareModel
import com.creatis.audition.data.database.room.models.TrackModel

@Dao
interface TrackDao {
    @Transaction
    @Query("SELECT * FROM Track")
    fun getTrackAndImage(): LiveData<List<TrackAndImages>>

    @Transaction
    @Query("SELECT * FROM Track")
    fun getTrackAndShare(): LiveData<List<TrackAndShare>>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertTrack(track: TrackModel)

    @Update
    fun updateTrack(track: TrackModel)

    @Insert(
        onConflict = OnConflictStrategy.REPLACE,
        entity = TrackModel::class
    )
    fun insertTrackList(tracks: List<TrackModel>)

    @Transaction
    fun insertTrackAndShare(shareDao: ShareDao, track: TrackModel, share: ShareModel) {
        val shareRowId : Long = shareDao.insertShare(share)
        val shareValue = shareDao.getShareByRowId(shareRowId).value
        if (shareValue != null){
            shareValue.trackId = track.trackId
            shareValue.let { shareDao.updateShare(it) }
            track.shareId = shareValue.shareId
            insertTrack(track)
        }
    }
    @Transaction
    fun insertTrackAndImage(imageDao: ImageDao, track: TrackModel, imagesModel: ImagesModel) {
        val imgRowId : Long = imageDao.insertImage(imagesModel)
        val imageValue = imageDao.getImageByRowId(imgRowId).value
        if (imageValue != null){
            imageValue.trackId = track.trackId
            imageValue.let { imageDao.updateImage(it) }
            track.imageId = imageValue.imageId
            insertTrack(track)
        }
    }



}

