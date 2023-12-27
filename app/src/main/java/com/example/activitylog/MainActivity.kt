package com.example.activitylog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.PieChart
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.activitylog.ui.theme.ActivityLogTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ActivityLogTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier.verticalScroll(rememberScrollState()),
                    ) {
                        QuickActionPanel()
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(
                                    20.dp
                                )
                        ) {
                            NewTaskPanel()
                            Spacer(modifier = Modifier.height(15.dp))
                            SuggestionsPanel()
                            Spacer(modifier = Modifier.height(15.dp))
                            StatsPanel()
                            Spacer(modifier = Modifier.height(15.dp))
                            HistoryPanel()
                            Spacer(modifier = Modifier.height(15.dp))
                            SettingsPanel()
                            Spacer(modifier = Modifier.height(15.dp))
                            Text("GNUv3 copyright by Hamza Nasher-Alneam, code on GitHub. Last edit Dec 26 2023")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuickActionPanel() {
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = Modifier.clip(
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomStart = 30.dp,
                bottomEnd = 30.dp
            )
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    20.dp
                ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Text("Example activity", fontSize = 50.sp, lineHeight = 55.sp)
            Text("10 minutes 13 seconds", fontSize = 30.sp, modifier = Modifier.padding(10.dp), color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold)
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)) {
                    AddIcon(iconType = Icons.Rounded.Close, description = "Cancel", 30.dp)
                }
                Button(onClick = { /*TODO*/ }, modifier = Modifier
                    .padding(horizontal = 15.dp),
                    shape = CircleShape
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 10.dp)
                    ) {
                        AddIcon(Icons.Rounded.PlayArrow, "Play Arrow", 60.dp)
                        // AddIcon(Icons.Rounded.Pause, "Pause", 60.dp)
                    }
                }
                Button(onClick = { /*TODO*/ }) {
                    AddIcon(iconType = Icons.Rounded.Check, description = "Complete", 30.dp)
                }
            }
        }
    }
}

@Composable
fun NewTaskPanel() {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    20.dp
                )
        ) {
            val text = remember { mutableStateOf("") }
            val isEnabled by remember { mutableStateOf(true) }
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AddIcon(iconType = Icons.Rounded.AddCircle, description = "New activity", 30.dp)
                Text("Create new activity", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(10.dp))
            }
            TextField(
                value = text.value,
                onValueChange = { newText -> text.value = newText },
                label = { Text("Enter your name") },
                modifier = Modifier.padding(vertical = 15.dp).fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                    disabledContainerColor = MaterialTheme.colorScheme.background,
                )
            )
            Button(onClick = { /*TODO*/ }, enabled = isEnabled) {
                Text("Start new activity named ${text.value}!")
            }
        }
    }
}

@Composable
fun SuggestionsPanel() {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    20.dp
                )
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AddIcon(iconType = Icons.Rounded.Star, description = "Suggestions", 30.dp)
                Text("Suggestions", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(10.dp))
            }
            ActivityDisplay("Example Task")
            ActivityDisplay("Another example")
            ActivityDisplay("Third item")
        }
    }
}

@Composable
fun ActivityDisplay(name: String) {
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth()
            .clip(
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Row {
            Text(name, modifier = Modifier.padding(vertical = 20.dp, horizontal = 30.dp))
        }
    }
}

@Composable
fun StatsPanel() {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    20.dp
                )
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AddIcon(iconType = Icons.Rounded.PieChart, description = "Statistics", 30.dp)
                Text("Stats", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(10.dp))
            }
        }
    }
}

@Composable
fun HistoryPanel() {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    20.dp
                )
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AddIcon(iconType = Icons.Rounded.History, description = "History", 30.dp)
                Text("History", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(10.dp))
            }
            ActivityDisplay("Another example")
            ActivityDisplay("Third item")
            ActivityDisplay("Example Task")
            ActivityDisplay("Third item")
            ActivityDisplay("Another example")
        }
    }
}

@Composable
fun SettingsPanel() {
    Surface(
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    20.dp
                )
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AddIcon(iconType = Icons.Rounded.Settings, description = "Settings", 30.dp)
                Text("Settings", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(10.dp))
            }
        }
    }
}

@Composable
fun AddIcon(iconType: ImageVector, description: String, size: Dp) {
    Icon(
        iconType,
        contentDescription = description,
        modifier = Modifier.size(size)
    )
}
