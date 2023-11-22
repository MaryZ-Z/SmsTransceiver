package com.android.smstransceiverapp.util

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.android.smstransceiverapp.data.repository.TelegramRepository
import com.android.smstransceiverapp.util.SmsBroadcastReceiver.Companion.MESSAGE_KEY
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class TelegramWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val repository: TelegramRepository
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        var result = Result.success()
        val message = inputData.getString(MESSAGE_KEY) ?: return Result.failure()
        try {
            repository.sendMessage(message)
        } catch (e: Exception) {
            result = Result.retry()
        }
        return result
    }
}