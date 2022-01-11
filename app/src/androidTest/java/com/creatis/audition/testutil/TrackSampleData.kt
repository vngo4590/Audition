package com.creatis.audition.testutil

import com.creatis.audition.R
import com.creatis.audition.data.database.room.TrackAndProperties
import com.creatis.audition.data.database.room.models.ImagesModel
import com.creatis.audition.data.database.room.models.ShareModel
import com.creatis.audition.data.database.room.models.TrackModel
import com.creatis.audition.data.database.room.util.TrackModelFactory

class TrackSampleData {
    companion object {
        fun generateTrackPropertiesList(numTracks: Int): List<TrackAndProperties> {
            val testTracks: MutableList<TrackAndProperties> = mutableListOf()
            for (i in 1..numTracks) {
                val track = TrackModelFactory.createTrackModel(
                    trackId = "track_$i",
                    title = "Track Title $i",
                    subtitle = "Track Subtitle $i",
                    type = "MUSIC",
                    shareId = "track_$i",
                    imageId = "track_$i"
                )
                val share = TrackModelFactory.createShareModel(
                    trackId = "track_$i",
                    subject = "Share Subject $i",
                    text = "Share Text $i",
                    image = R.mipmap.ic_launcher.toString()
                )
                val image = TrackModelFactory.createImagesModel(
                    trackId = "track_$i",
                    background = "Image Background $i",
                    coverArt = "Image Cover Art $i"
                )

                testTracks.add(
                    TrackAndProperties(
                        track = track,
                        share = share,
                        images = image
                    )
                )

            }
            return testTracks
        }

        /*fun generateTrackList(numTracks: Int): List<TrackModel> {
            val testTracks: MutableList<TrackModel> = mutableListOf()
            for (i in 0..numTracks) {
                testTracks.add(
                    TrackModelFactory.createTrackModel(
                        trackId = "track_$i",
                        title = "Track Title $i",
                        subtitle = "Track Subtitle $i",
                        type = "MUSIC",
                        shareId = "track_$i",
                        imageId = "track_$i"
                    )
                )
            }
            return testTracks
        }

        fun generateShareList(numShares: Int): List<ShareModel> {
            val testShares: MutableList<ShareModel> = mutableListOf()
            for (i in 0..numShares) {
                testShares.add(
                    TrackModelFactory.createShareModel(
                        trackId = "track_$i",
                        subject = "Share Subject $i",
                        text = "Share Text $i",
                        image = R.mipmap.ic_launcher.toString()
                    )
                )
            }
            return testShares
        }

        fun generateImagesList(numImages: Int): List<ImagesModel> {
            val testShares: MutableList<ImagesModel> = mutableListOf()
            for (i in 0..numImages) {
                testShares.add(
                    TrackModelFactory.createImagesModel(
                        trackId = "track_$i",
                        background = "Image Background $i",
                        coverArt = "Image Cover Art $i"
                    )
                )
            }
            return testShares
        }*/
    }
}

