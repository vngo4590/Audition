package com.creatis.audition.data.database.room.util

import androidx.annotation.Nullable
import com.creatis.audition.data.database.room.models.ImagesModel
import com.creatis.audition.data.database.room.models.ShareModel
import com.creatis.audition.data.database.room.models.TrackModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


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
/**
* Thís Extension function allows us to wait for value
* */
@Throws(InterruptedException::class)
suspend inline fun <reified T> LiveData<T>.getOrAwaitValue(): T? {
    val data = arrayOfNulls<T>(1)
    val latch = CountDownLatch(1)
    val liveData = this
    val observer: Observer<T> = object : Observer<T> {
        override fun onChanged(o: T?) {
            data[0] = o
            latch.countDown()
            liveData.removeObserver(this)
        }
    }
    coroutineScope {
        val job = launch(Dispatchers.Main) {
            liveData.observeForever(observer)
        }
        job.join()
    }
    latch.await(2, TimeUnit.SECONDS)
    return data[0]
}