package com.creatis.audition.ui.playtrack

import android.app.Application
import androidx.lifecycle.*
import com.creatis.audition.data.database.PlayTrackRepository
import com.creatis.audition.data.database.room.TrackAndProperties
import com.creatis.audition.data.database.room.TrackDatabase
import com.creatis.audition.data.network.ServiceUtil
import kotlinx.coroutines.launch
import timber.log.Timber

class PlayTrackChartViewModel(application: Application) : AndroidViewModel(application) {
    private val shazamApiService = ServiceUtil.serviceApiCreate()
    private val trackDatabase: TrackDatabase = TrackDatabase.getDatabase(application)
    private val playTrackRepository: PlayTrackRepository =
        PlayTrackRepository(trackDatabase, shazamApiService)

    /*
    * Variables
    * */
    val playTrackCharts: LiveData<List<TrackAndProperties>>
        get() {
            return playTrackRepository.playTrackCharts
        }


    init {
        viewModelScope.launch {
            playTrackRepository.fetchChartTracks()
//            Timber.i(playTrackRepository.playTrackCharts.value.toString())
        }
    }
}