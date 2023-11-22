package com.android.smstransceiverapp.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Telephony
import androidx.work.*
import com.android.smstransceiverapp.R
import com.android.smstransceiverapp.data.repository.DataStoreRepository
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class SmsBroadcastReceiver : BroadcastReceiver() {

    @Inject
    lateinit var constraints: Constraints

    @Inject
    lateinit var workManager: WorkManager

    @Inject
    lateinit var repository: DataStoreRepository

    private val deviceName = "${Build.MANUFACTURER} ${Build.MODEL}"

    override fun onReceive(context: Context?, intent: Intent?) {
        val messages = Telephony.Sms.Intents.getMessagesFromIntent(intent)

        messages?.let {
            val originatingAddress = messages.firstOrNull()?.originatingAddress
            val messageBody = messages.joinToString("") { it.messageBody }

            context?.getString(R.string.message_body)?.let {
                val message = it.format(deviceName, originatingAddress, messageBody)
                val requests = mutableListOf<WorkRequest>()
                if (repository.getTelegram()) {
                    requests.add(
                        OneTimeWorkRequestBuilder<TelegramWorker>()
                            .setInputData(workDataOf(MESSAGE_KEY to message))
                            .setConstraints(constraints)
                            .setBackoffCriteria(
                                BackoffPolicy.LINEAR,
                                WorkRequest.Companion.MIN_BACKOFF_MILLIS,
                                TimeUnit.MILLISECONDS
                            )
                            .build()
                    )
                }
                if (requests.isNotEmpty()) workManager.enqueue(requests)
            }
        }
    }

    companion object {
        const val MESSAGE_KEY = "message_key"
    }
}