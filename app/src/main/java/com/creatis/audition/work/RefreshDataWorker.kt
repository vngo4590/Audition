package com.creatis.audition.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.creatis.audition.data.database.PlayTrackRepository
import com.creatis.audition.data.database.room.TrackDatabase
import com.creatis.audition.data.network.ServiceUtil
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {
    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val shazamApiService = ServiceUtil.serviceApiCreate()
        val trackDatabase: TrackDatabase = TrackDatabase.getDatabase(this.applicationContext)
        val repository = PlayTrackRepository(trackDatabase, shazamApiService)
        return try {
            repository.fetchChartTracks()
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}