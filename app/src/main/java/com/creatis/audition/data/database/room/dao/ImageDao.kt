package com.creatis.audition.data.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.creatis.audition.data.database.room.models.ImageModel
@Dao
interface ImageDao {
    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertImage(image: ImageModel) : Long

    @Query(
        "SELECT * FROM Image WHERE track_id = :trackId"
    )
    fun getImageByTrackId(trackId: String): LiveData<ImageModel>

    @Query(
        "SELECT * FROM Image WHERE image_id = :imageId"
    )
    fun getImageByImageId(imageId: Long): LiveData<ImageModel>

    @Query(
        "SELECT * FROM Image WHERE rowId = :rowId"
    )
    fun getImageByRowId(rowId: Long): LiveData<ImageModel>


    @Query(
        "SELECT * FROM Image"
    )
    fun getImages(): LiveData<List<ImageModel>>

    @Update
    fun updateImage(image: ImageModel)
}