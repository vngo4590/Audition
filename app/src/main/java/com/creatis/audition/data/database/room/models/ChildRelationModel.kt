package com.creatis.audition.data.database.room.models

interface ChildRelationModel {
    fun setParentId(id : String)
    fun getParentId() : String?
}