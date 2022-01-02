package com.creatis.audition.data.database.room

import androidx.room.TypeConverter
import com.creatis.audition.data.database.room.models.ShareModel
import com.creatis.audition.data.playtrack.playtracklist.Share

class Converters {
    companion object {
        @TypeConverter
        fun fromShareToShareModel(share: Share): ShareModel {
            return ShareModel(
                subject = share.subject,
                text = share.text,
                href = share.href,
                image = share.image,
                twitter = share.twitter,
                html = share.html,
                avatar = share.avatar,
                snapchat = share.snapchat
            )
        }
    }
}