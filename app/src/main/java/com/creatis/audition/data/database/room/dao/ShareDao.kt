package com.creatis.audition.data.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.creatis.audition.data.database.room.models.ShareModel
@Dao
interface ShareDao {
    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertShare(share: ShareModel): Long

    @Query(
        "SELECT * FROM Share WHERE track_id = :trackId"
    )
    fun getShareByTrackId(trackId: String) : ShareModel?

    @Query(
        "SELECT * FROM Share WHERE share_id = :shareId"
    )
    fun getShareByShareId(shareId: Long) : ShareModel?
    @Query(
        "SELECT * FROM Share"
    )
    fun getShares() : LiveData<List<ShareModel>>
    @Query(
        "SELECT * FROM Share WHERE rowId = :rowId"
    )
    fun getShareByRowId(rowId: Long): LiveData<ShareModel>
    @Update
    fun updateShare(share: ShareModel)
}