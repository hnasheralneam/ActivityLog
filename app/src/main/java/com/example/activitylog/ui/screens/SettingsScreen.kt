package com.example.activitylog.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.activitylog.SettingsPanel

@Composable
fun SettingsScreen() {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize().padding(bottom = 80.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    20.dp
                ).verticalScroll(rememberScrollState())
        ) {
            SettingsPanel()
            Spacer(modifier = Modifier.height(15.dp))
            Text("GNUv3 copyright by Hamza Nasher-Alneam, code on GitHub. Last edit on Jan 7 2024")
        }
    }
}