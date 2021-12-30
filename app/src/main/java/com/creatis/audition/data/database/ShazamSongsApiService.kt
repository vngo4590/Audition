package com.creatis.audition.data.database

import com.creatis.audition.data.playtrack.chartlist.ChartList
import com.creatis.audition.data.playtrack.playtracklist.PlayTracks
import com.creatis.audition.data.playtrack.playtracklist.Track
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * A retrofit service to fetch playlist from Shazam API.
 */
interface ShazamSongsApiService {
    /**
     * Returns the "future" results of Top PlayTrack gotten from the API
     * */
    @GET("songs/list-artist-top-tracks")
    fun getArtistTopTracksAsync(
        @Query("id") id: String,
        @Query("locale") locale: String? = "en-US"
    ): Deferred<PlayTracks>

    /**
     * Returns the "future" results of Recommended PlayTrack gotten from the API
     * */
    @GET("songs/list-recommendations")
    fun getRecommendedTracksAsync(
        @Query("key") key: String,
        @Query("locale") locale: String? = "en-US"
    ): Deferred<PlayTracks>

    /**
     * Returns the "future" results of Recommended PlayTrack gotten from the API
     * */
    @GET("songs/get-details")
    fun getTrackDetailsAsync(
        @Query("key") key: String,
        @Query("locale") locale: String? = "en-US"
    ): Deferred<Track>

    /**
     * Returns the "future" results of Track Charts gotten from the API
     * */
    @GET("charts/track")
    fun getChartTrackAsync(
        @Query("locale") locale: String? = "en-US", @Query("listId") listId: String?,
        @Query("pageSize") pageSize: Int? = 20, @Query("startFrom") startFrom: Int? = 0
    ): Deferred<PlayTracks>

    /**
     * Returns the "future" results of Track Charts gotten from the API
     * */
    @GET("charts/list")
    fun getChartListAsync(): Deferred<ChartList>


}