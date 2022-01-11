package com.creatis.audition

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.creatis.audition.data.database.room.TrackAndImages
import com.creatis.audition.data.database.room.TrackAndProperties
import com.creatis.audition.data.database.room.TrackAndShare
import com.creatis.audition.data.database.room.TrackDatabase
import com.creatis.audition.data.database.room.dao.ImageDao
import com.creatis.audition.data.database.room.dao.ShareDao
import com.creatis.audition.data.database.room.dao.TrackDao
import com.creatis.audition.data.database.room.dao.TrackRelationDao
import com.creatis.audition.data.database.room.models.ImagesModel
import com.creatis.audition.data.database.room.models.ShareModel
import com.creatis.audition.data.database.room.models.TrackModel
import com.creatis.audition.data.database.room.util.TrackModelFactory
import com.creatis.audition.data.database.room.util.getOrAwaitValue
import com.creatis.audition.testutil.CoroutineTestRule
import com.creatis.audition.testutil.TrackSampleData
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestDispatcher
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import java.io.IOException
import kotlin.concurrent.thread

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class TrackDaoReadWriteTest {
    private lateinit var trackDao: TrackDao
    private lateinit var shareDao: ShareDao
    private lateinit var imageDao: ImageDao
    private lateinit var trackRelationDao: TrackRelationDao

    private lateinit var trackDatabase: TrackDatabase

    private val numTracks = 10
    private var trackPropertiesList: List<TrackAndProperties> =
        TrackSampleData.generateTrackPropertiesList(10)

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        trackDatabase = Room.inMemoryDatabaseBuilder(
            context, TrackDatabase::class.java
        ).build()
        trackDao = trackDatabase.trackDao
        shareDao = trackDatabase.shareDao
        imageDao = trackDatabase.imageDao
        trackRelationDao = trackDatabase.trackRelationDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        trackDatabase.close()
    }

    /*
    * Testing Track and Share Dao's
    * */
    @Test
    fun writeGetTrackAndShare() = runBlocking {
        val trackTitle = "Hello World"
        val trackArtist = "Adele"
        val trackId = "sampleTrack"
        val sampleTrack = TrackModelFactory.createTrackModel(
            trackId = trackId,
            title = trackTitle, subtitle = trackArtist
        )

        val sampleShare = TrackModelFactory.createShareModel(
            trackId = trackId,
            text = trackTitle + trackArtist
        )
        sampleTrack.shareId = sampleShare.trackId

        val deferred = async {
            trackDao.insertTrack(sampleTrack)
            trackRelationDao.insertTrackAndShare(
                trackId = trackId,
                shareModel = sampleShare,
                trackDatabase = trackDatabase
            )
        }
        deferred.await()

        var trackAndShare: TrackAndShare? = trackRelationDao.getTrackAndShareById("trackId")
        assertNull("TrackAndShare must be NULL", trackAndShare)
        trackAndShare = trackRelationDao.getTrackAndShareById(trackId)
        assertNotNull("Could not find TrackAndShare", trackAndShare)
        assertNotNull("Share is not found", trackAndShare?.share)
        assertEquals("Obtained is wrong Share", trackAndShare?.share, sampleShare)
        assertEquals("Obtained is wrong Track", trackAndShare?.track, sampleTrack)
        val trackAndShareList: List<TrackAndShare> = trackRelationDao.getTrackAndShare()
        assertEquals(
            "Size must be 1 but received : %d".format(trackAndShareList.size),
            trackAndShareList.size,
            1
        )
        assertEquals(
            "Share %s is not equal to the inserted data %s".format(
                trackAndShareList[0].toString(),
                trackAndShare.toString()
            ), trackAndShareList[0].share, trackAndShare?.share
        )
        assertEquals(
            "Track %s is not equal to the inserted data %s".format(
                trackAndShareList[0].toString(),
                trackAndShare.toString()
            ), trackAndShareList[0].track, trackAndShare?.track
        )
    }

    /*
    * Testing Track and Images Dao's
    * */
    @Test
    fun writeGetTrackAndImage() = runBlocking {
        val trackTitle = "Hello World"
        val trackArtist = "Adele"
        val trackId = "sampleTrack"
        val sampleTrack = TrackModelFactory.createTrackModel(
            trackId = trackId,
            title = trackTitle, subtitle = trackArtist
        )

        val sampleImages = TrackModelFactory.createImagesModel(
            coverArt = "abc123",
            background = "abc123"
        )

        sampleImages.trackId = sampleTrack.trackId
        sampleTrack.imageId = sampleImages.trackId

        val deferred = async {
            trackDao.insertTrack(sampleTrack)
            trackRelationDao.insertTrackAndImage(
                trackId = trackId,
                images = sampleImages,
                trackDatabase = trackDatabase
            )
        }

        deferred.await()

        val trackAndImages: TrackAndImages? = trackRelationDao.getTrackAndImagesById(trackId)

        assertNotNull("Could not find TrackAndImages", trackAndImages)
        assertNotNull(
            "Images is not found by result %s".format(trackAndImages.toString()),
            trackAndImages?.images
        )
        assertEquals("Obtained is wrong Images", trackAndImages?.images, sampleImages)
        assertEquals("Obtained is wrong Track", trackAndImages?.track, sampleTrack)

        val trackAndImagesList: List<TrackAndImages> = trackRelationDao.getTrackAndImages()
        assertEquals(
            "Size must be 1 but received : %d".format(trackAndImagesList.size),
            trackAndImagesList.size,
            1
        )
        assertEquals(
            "Images %s is not equal to the inserted data %s".format(
                trackAndImagesList[0].toString(),
                sampleImages.toString()
            ), trackAndImagesList[0].images, sampleImages
        )
    }

    @Test
    fun writeGetTrackAndProperties() = runBlocking {
        trackRelationDao.insertTrackAndPropertiesList(trackDatabase, trackPropertiesList)

        val trackList = trackRelationDao.getTrackAndProperties().getOrAwaitValue()

        assertNotNull("Retrieved result is null", trackList)
        val trackMap: Map<String, List<TrackAndProperties>> =
            (trackList?.groupBy { it.track.trackId } ?: assertEquals(
                "The retrieved list is missing some information",
                numTracks,
                trackList?.size
            )) as Map<String, List<TrackAndProperties>>

        for (track in trackPropertiesList) {
            assertEquals(
                "%s Does not have equal values".format(track.track.trackId),
                track,
                trackMap[track.track.trackId]?.get(0)
            )
        }
    }
}