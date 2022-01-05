package com.creatis.audition.data.database.room.models

interface TrackRelationModel {
    fun setParentId(id : String)
    fun getParentId() : String?
}