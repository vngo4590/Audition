package com.creatis.audition.data.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.creatis.audition.data.database.room.models.ImagesModel
@Dao
interface ImageDao {
    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertImage(images: ImagesModel) : Long

    @Query(
        "SELECT * FROM Images WHERE track_id = :trackId"
    )
    fun getImageByTrackId(trackId: String): ImagesModel?

    @Query(
        "SELECT * FROM Images WHERE image_id = :imageId"
    )
    fun getImageByImageId(imageId: Long): ImagesModel?

    @Query(
        "SELECT * FROM Images WHERE rowId = :rowId"
    )
    fun getImageByRowId(rowId: Long): ImagesModel?


    @Query(
        "SELECT * FROM Images"
    )
    fun getImages(): LiveData<List<ImagesModel>>

    @Update
    fun updateImage(images: ImagesModel)
}