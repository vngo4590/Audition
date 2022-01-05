package com.creatis.audition.data.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.creatis.audition.data.database.room.Converters
import com.creatis.audition.data.database.room.TrackAndImages
import com.creatis.audition.data.database.room.TrackAndShare
import com.creatis.audition.data.database.room.models.TrackModel
import com.creatis.audition.data.playtrack.playtracklist.Track

@Dao
@TypeConverters(Converters::class)
interface TrackDao {
    @Query(
        "SELECT * FROM Track WHERE rowId = :rowId"
    )
    fun getTrackByRowId(rowId: Long): LiveData<TrackModel>

    @Query(
        "SELECT * FROM Track WHERE track_id = :trackId"
    )
    fun getTrackByTrackId(trackId: String): LiveData<TrackModel>

    @Transaction
    @Query("SELECT * FROM Track")
    fun getTrackAndImage(): LiveData<List<TrackAndImages>>

    @Transaction
    @Query("SELECT * FROM Track")
    fun getTrackAndShare(): LiveData<List<TrackAndShare>>

    /*@Insert(
        onConflict = OnConflictStrategy.REPLACE,
        entity = TrackModel::class
    )
    fun insertTrackAndShare(trackAndShare: TrackAndShare)

    @Insert(
        onConflict = OnConflictStrategy.REPLACE,
        entity = TrackModel::class
    )
    fun insertTrackAndImage(trackAndImages: TrackAndImages)*/

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertTrack(track: TrackModel): Long

    @Insert(
        onConflict = OnConflictStrategy.REPLACE,
        entity = TrackModel::class
    )
    fun insertTracks(tracks: List<TrackModel>)

    @Update
    fun updateTrack(track: TrackModel)

}

