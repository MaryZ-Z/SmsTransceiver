package com.android.smstransceiverapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.android.smstransceiverapp.ui.theme.SmsTransceiverAppTheme
import com.android.smstransceiverapp.ui.main.MainScreen

@Composable
fun SmsTransceiverApp() {
    SmsTransceiverAppTheme {
        Scaffold { innerPadding ->
            MainScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun CenteredText(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
fun Button(text: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = text)
    }
}

@Composable
fun Switch(titleResId: Int, checked: Boolean, onCheckedChanged: (Boolean) -> Unit) {
    ListItem(
        headlineContent = { Text(text = stringResource(id = titleResId)) },
        trailingContent = { Switch(checked = checked, onCheckedChange = onCheckedChanged) }
    )
}