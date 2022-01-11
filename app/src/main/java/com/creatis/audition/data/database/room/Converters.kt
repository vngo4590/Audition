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
        private fun Share.toShareModel(): ShareModel = with(::ShareModel) {
            val propertiesByName = Share::class.memberProperties.associateBy { it.name }
            callBy(args = parameters.associateWith { parameter ->
                propertiesByName[parameter.name]?.get(this@toShareModel)
            })
        }

        private fun Images.toImagesModel(): ImagesModel = with(::ImagesModel) {
            val propertiesByName = Images::class.memberProperties.associateBy { it.name }
            callBy(args = parameters.associateWith { parameter ->
                propertiesByName[parameter.name]?.get(this@toImagesModel)
            })
        }
    }

    private fun fromShareToShareModel(trackId: String, share: Share): ShareModel {
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
        return ShareModel(
            trackId = trackId,
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

    @TypeConverter
    fun fromShareModelToShare(share: ShareModel): Share {
        return Share(
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

    private fun fromImagesToImagesModel(trackId: String, image: Images): ImagesModel {
        return ImagesModel(
            trackId = trackId,
            background = image.background,
            coverArt = image.coverArt,
            coverArtHq = image.coverArtHq,
            joeColor = image.joeColor,
            overflow = image.overflow,
            defaultValue = image.default
        )
    }

    @TypeConverter
    fun fromImagesModelToImages(image: ImagesModel): Images {
        return Images(
            background = image.background,
            coverArt = image.coverArt,
            coverArtHq = image.coverArtHq,
            joeColor = image.joeColor,
            overflow = image.overflow,
            default = image.defaultValue,
        )
    }

    @TypeConverter
    fun fromTrackToTrackModel(track: Track): TrackModel {
        return TrackModel(
            trackId = track.key,
            layout = track.layout,
            type = track.type,
            title = track.title,
            subtitle = track.subtitle,
            url = track.url,
            shareId = track.key,
        )
    }

    @TypeConverter
    fun fromTrackToTrackProperties(track: Track): TrackAndProperties {
        val trackModel = fromTrackToTrackModel(track)
        val trackId = trackModel.trackId
        val imagesModel = track.images?.let { fromImagesToImagesModel(trackId, it) }
        imagesModel?.trackId = trackId
        val shareModel = fromShareToShareModel(trackId, track.share)
        shareModel.trackId = trackId
        return TrackAndProperties(trackModel, shareModel, imagesModel)
    }
}