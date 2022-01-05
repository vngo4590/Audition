package com.creatis.audition.data.database.room

import androidx.room.TypeConverter
import com.creatis.audition.data.database.room.models.ImagesModel
import com.creatis.audition.data.database.room.models.ShareModel
import com.creatis.audition.data.database.room.models.TrackModel
import com.creatis.audition.data.playtrack.playtracklist.Images
import com.creatis.audition.data.playtrack.playtracklist.Share
import com.creatis.audition.data.playtrack.playtracklist.Track
import kotlin.reflect.full.memberProperties

class Converters {
    companion object {

        /*
        * These methods are not ideal for performance but flexible enough for changes
        * */
        private fun Share.toShareModel() : ShareModel = with(::ShareModel) {
            val propertiesByName = Share::class.memberProperties.associateBy { it.name }
            callBy(args = parameters.associateWith { parameter ->
                propertiesByName[parameter.name]?.get(this@toShareModel)
            })
        }
        private fun Images.toImagesModel() : ImagesModel = with(::ImagesModel) {
            val propertiesByName = Images::class.memberProperties.associateBy { it.name }
            callBy(args = parameters.associateWith { parameter ->
                propertiesByName[parameter.name]?.get(this@toImagesModel)
            })
        }
    }
    @TypeConverter
    fun fromShareToShareModel(share: Share): ShareModel {
        /*ShareModel(
            subject = share.subject,
            text = share.text,
            href = share.href,
            image = share.image,
            twitter = share.twitter,
            html = share.html,
            avatar = share.avatar,
            snapchat = share.snapchat
        )*/
        return share.toShareModel()
    }
    @TypeConverter
    fun fromImagesToImageModel(image: Images): ImagesModel {
        return image.toImagesModel()
    }
    @TypeConverter
    fun fromTrackToTrackModel(track: Track): TrackModel {
        return TrackModel(
            trackId = track.key,
            layout = track.layout,
            type = track.type,
            title = track.title,
            subtitle = track.subtitle,
            url = track.url
        )
    }
}