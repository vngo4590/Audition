package com.creatis.audition.data.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.creatis.audition.data.database.room.Converters
import com.creatis.audition.data.database.room.TrackAndImages
import com.creatis.audition.data.database.room.TrackAndProperties
import com.creatis.audition.data.database.room.TrackAndShare
import com.creatis.audition.data.database.room.models.TrackModel

@Dao
@TypeConverters(Converters::class)
interface TrackDao : ImageDao, ShareDao{
    @Query(
        "SELECT * FROM Track WHERE rowId = :rowId"
    )
    fun getTrackByRowId(rowId: Long): TrackModel?

    @Query(
        "SELECT * FROM Track WHERE track_id = :trackId"
    )
    fun getTrackByTrackId(trackId: String): TrackModel?

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

