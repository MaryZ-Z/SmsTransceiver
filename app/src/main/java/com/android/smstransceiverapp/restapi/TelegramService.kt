package com.android.smstransceiverapp.restapi

import retrofit2.http.GET
import retrofit2.http.Query

interface TelegramService {
    @GET("sendMessage")
    suspend fun sendMessage(
        @Query("chat_id") chatId: String,
        @Query("text") text: String
    )
}