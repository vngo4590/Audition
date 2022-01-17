package com.creatis.audition.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.creatis.audition.data.database.PlayTrackRepository
import com.creatis.audition.data.database.room.TrackAndProperties
import com.creatis.audition.data.database.room.TrackDatabase
import com.creatis.audition.data.network.ServiceUtil
import kotlinx.coroutines.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val shazamApiService = ServiceUtil.serviceApiCreate()
    private val trackDatabase: TrackDatabase = TrackDatabase.getDatabase(application)
    private val playTrackRepository: PlayTrackRepository =
        PlayTrackRepository(trackDatabase, shazamApiService)

    val topPlayTracks: LiveData<List<TrackAndProperties>> = playTrackRepository.topPlayTrackCharts

    init {
        viewModelScope.launch {
            playTrackRepository.fetchChartTracks()
        }
    }

}