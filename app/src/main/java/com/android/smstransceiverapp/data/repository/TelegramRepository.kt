package com.android.smstransceiverapp.data.repository

import com.android.smstransceiverapp.restapi.TelegramService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TelegramRepository @Inject constructor(
    private val service: TelegramService
) {
    private val telegramId = "957481317"

    suspend fun sendMessage(message: String) {
        service.sendMessage(telegramId, message)
    }
}