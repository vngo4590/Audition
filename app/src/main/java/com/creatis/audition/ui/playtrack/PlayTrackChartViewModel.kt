package com.creatis.audition.ui.playtrack

import android.app.Application
import androidx.lifecycle.*
import com.creatis.audition.data.database.PlayTrackRepository
import com.creatis.audition.data.database.room.TrackDatabase
import com.creatis.audition.data.network.ServiceUtil
import com.creatis.audition.data.playtrack.playtracklist.Track
import kotlinx.coroutines.launch

class PlayTrackChartViewModel(application: Application) : AndroidViewModel(application) {
    private val shazamApiService = ServiceUtil.serviceApiCreate()
    private val trackDatabase: TrackDatabase = TrackDatabase.getDatabase(application)
    private val playTrackRepository: PlayTrackRepository =
        PlayTrackRepository(trackDatabase, shazamApiService)

    /*
    * Variables
    * */
    val playTrackCharts: MutableLiveData<List<Track>?>
        get() = playTrackRepository.playTrackCharts


    init {
        viewModelScope.launch {
            playTrackRepository.fetchChartTracks()
        }
    }
}