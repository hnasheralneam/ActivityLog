package com.example.activitylog.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.activitylog.HistoryPanel

@Composable
fun HistoryScreen() {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize().padding(bottom = 80.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(
                20.dp
            ).verticalScroll(rememberScrollState())
        ) {
            HistoryPanel()
        }
    }
}