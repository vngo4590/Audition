package com.creatis.audition.ui.playtrack

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creatis.audition.data.database.PlayTrackRepository
import com.creatis.audition.data.network.ServiceUtil
import com.creatis.audition.data.playtrack.playtracklist.Track
import kotlinx.coroutines.launch

class PlayTrackChartViewModel : ViewModel() {
    private val shazamApiService = ServiceUtil.serviceApiCreate()
    private val playTrackRepository: PlayTrackRepository = PlayTrackRepository(shazamApiService)

    /*
    * Variables
    * */
    val playTrackCharts : MutableLiveData<List<Track>?>
        get() = playTrackRepository.playTrackCharts


    init {
        viewModelScope.launch {
            playTrackRepository.fetchChartTracks()
        }
    }
}