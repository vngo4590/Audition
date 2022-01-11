package com.creatis.audition.data.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.creatis.audition.data.database.room.Converters
import com.creatis.audition.data.database.room.TrackAndProperties
import com.creatis.audition.data.database.room.TrackDatabase
import com.creatis.audition.data.playtrack.chartlist.ChartList
import com.creatis.audition.data.playtrack.playtracklist.PlayTracks
import com.creatis.audition.data.playtrack.playtracklist.Track
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlayTrackRepository(
    private val trackDatabase: TrackDatabase,
    private val shazamSongsApiService: ShazamSongsApiService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    /*
    * Variables
    * */
    /*private val _playTrackCharts = MutableLiveData<List<Track>?>()
    val playTrackCharts: MutableLiveData<List<Track>?>
        get() = _playTrackCharts*/
    private var _playTrackCharts: MutableLiveData<List<TrackAndProperties>> =
        MutableLiveData()
    val playTrackCharts: LiveData<List<TrackAndProperties>>
        get() = _playTrackCharts

    private val converters = Converters()

    /**
     * Get the artist's top tracks stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     */
    suspend fun fetchArtistTopTracks(id: String, locale: String? = "en-US"): PlayTracks =
        withContext(ioDispatcher) {
            shazamSongsApiService.getArtistTopTracksAsync(id, locale).await()
        }

    /**
     * Fetch recommended tracks
     * */
    suspend fun fetchRecommendedTracks(key: String, locale: String? = "en-US"): PlayTracks =
        withContext(ioDispatcher) {
            shazamSongsApiService.getRecommendedTracksAsync(key, locale).await()
        }

    /**
     * Fetch a track's details
     * */
    suspend fun fetchTrackDetails(key: String, locale: String? = "en-US"): Track =
        withContext(ioDispatcher) {
            shazamSongsApiService.getTrackDetailsAsync(key, locale).await()
        }

    /**
     * Fetch chart of tracks
     * */
    suspend fun fetchChartTracks(
        locale: String? = "en-US", listId: String? = null,
        pageSize: Int? = 20, startFrom: Int? = 0
    ) {
        withContext(ioDispatcher) {
            val tracks =
                shazamSongsApiService.getChartTrackAsync(locale, listId, pageSize, startFrom)
                    .await().tracks
            val trackProperties: List<TrackAndProperties>? = tracks?.map {
                converters.fromTrackToTrackProperties(it)
            }
            trackProperties?.let {
                trackDatabase.trackRelationDao.insertTrackAndPropertiesList(
                    trackDatabase,
                    it
                )
            }
            /*
            * Call to update once done
            * */
            updateTrackData()
        }
    }

    private fun updateTrackData() {
        _playTrackCharts.postValue(
            trackDatabase.trackRelationDao.getTrackAndProperties()
        )
    }

    /**
     * Get the chartlist stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     */
    suspend fun fetchChartList(): ChartList =
        withContext(ioDispatcher) { shazamSongsApiService.getChartListAsync().await() }

}
