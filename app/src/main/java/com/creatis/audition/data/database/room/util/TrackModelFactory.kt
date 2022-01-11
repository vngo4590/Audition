package com.creatis.audition.data.database.room.util

import com.creatis.audition.data.database.room.models.ImagesModel
import com.creatis.audition.data.database.room.models.ShareModel
import com.creatis.audition.data.database.room.models.TrackModel

/**
 * Factory class of TrackModel and its children
 * */
class TrackModelFactory {
    companion object {
        fun createTrackModel(
            trackId: String = "Sample",
            layout: String = "Layout",
            type: String = "Type",
            title: String = "Track Title",
            subtitle: String = "Artist",
            shareId: String? = null,
            imageId: String? = null,
            url: String = "Sample",
        ): TrackModel {
            return TrackModel(
                trackId = trackId,
                layout = layout,
                type = type,
                title = title,
                subtitle = subtitle,
                url = url,
                shareId = shareId,
                imageId = imageId
            )
        }

        fun createShareModel(
            trackId: String = "Sample",
            subject: String = "Sample",
            text: String = "Sample",
            href: String = "Sample",
            image: String? = null,
            twitter: String? = null,
            html: String = "Sample",
            avatar: String? = null,
            snapchat: String? = null
        ): ShareModel {
            return ShareModel(
                trackId = trackId,
                subject = subject,
                text = text,
                href = href,
                image = image,
                twitter = twitter,
                html = html,
                avatar = avatar,
                snapchat = snapchat
            )
        }

        fun createImagesModel(
            trackId: String = "Sample",
            background: String? = null,
            coverArt: String? = null,
            coverArtHq: String? = null,
            joeColor: String? = null,
            overflow: String? = null,
            defaultValue: String? = null,
        ): ImagesModel {
            return ImagesModel(
                trackId = trackId,
                background = background,
                coverArt = coverArt,
                coverArtHq = coverArtHq,
                joeColor = joeColor,
                overflow = overflow,
                defaultValue = defaultValue
            )
        }

    }
}