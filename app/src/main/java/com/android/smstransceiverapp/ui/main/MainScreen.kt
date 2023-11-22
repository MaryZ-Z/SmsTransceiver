package com.android.smstransceiverapp.ui.main

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.smstransceiverapp.R
import com.android.smstransceiverapp.ui.components.Button
import com.android.smstransceiverapp.ui.components.CenteredText
import com.android.smstransceiverapp.ui.components.Switch
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val viewModel: MainViewModel = hiltViewModel()
    val isTelegramEnabled by viewModel.isTelegramEnabled.collectAsState()
    val smsPermissionState = rememberPermissionState(Manifest.permission.RECEIVE_SMS)

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (smsPermissionState.status.isGranted) {
            CenteredText(text = stringResource(id = R.string.working_message))
            Spacer(modifier = Modifier.height(16.dp))
            Switch(
                titleResId = R.string.telegram_title,
                checked = isTelegramEnabled,
                onCheckedChanged = viewModel::changeTelegram
            )
        } else {
            CenteredText(text = stringResource(id = R.string.sms_receive_permission_rationale))
            Spacer(modifier = Modifier.height(8.dp))
            Button(text = stringResource(id = R.string.button_positive)) {
                smsPermissionState.launchPermissionRequest()
            }
        }
    }
}