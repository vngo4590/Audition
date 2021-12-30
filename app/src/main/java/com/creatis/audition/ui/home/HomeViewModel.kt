package com.creatis.audition.ui.home

import android.app.Service
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creatis.audition.data.database.PlayTrackRepository
import com.creatis.audition.data.network.ServiceUtil
import kotlinx.coroutines.*
import timber.log.Timber

class HomeViewModel : ViewModel() {

    private val shazamApiService = ServiceUtil.serviceApiCreate()
    private val playTrackRepository: PlayTrackRepository = PlayTrackRepository(shazamApiService)

    private val _text = MutableLiveData<String>().apply {
        value = "Hello Home"
    }
    val text: LiveData<String> = _text

    init {
        viewModelScope.launch {
            _text.value = playTrackRepository.fetchChartTracks().toString()
        }
    }

}