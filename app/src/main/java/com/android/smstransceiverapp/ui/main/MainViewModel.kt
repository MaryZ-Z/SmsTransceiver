package com.android.smstransceiverapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.smstransceiverapp.data.repository.DataStoreRepository
import com.android.smstransceiverapp.extensions.logd
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {
    val isTelegramEnabled = dataStoreRepository.isTelegramEnabled

    fun changeTelegram(value: Boolean) {
        viewModelScope.launch {
            try {
                dataStoreRepository.saveTelegram(value)
            } catch (e: Exception) {
                logd(e)
            }
        }
    }
}