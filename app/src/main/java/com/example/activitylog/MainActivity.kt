package com.example.activitylog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.PieChart
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.activitylog.ui.screens.MainScreen
import com.example.activitylog.ui.theme.ActivityLogTheme
import java.time.LocalDateTime
import java.util.UUID


// to add
// start new icon for old tasks to repeat
// hide history in different panel
// hide settings in different panel
// add stats chart
// add average/total time
// add history details
// clear save, export/import save

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ActivityLogTheme {
                MainScreen()
            }
        }
    }
}

data class Activity(val name: String, val seconds: Int, val paused: Boolean, val start: LocalDateTime, val finish: LocalDateTime, val id: String)
val placeholderActivity: Activity = Activity("placeholder", 221, true, LocalDateTime.now(), LocalDateTime.now(), "20123")
var activityRunning: Boolean = true
var currentActivity: Activity = placeholderActivity
val activities: MutableList<Activity> = mutableListOf()
val completedActivities: MutableList<Activity> = mutableListOf()

var activeActivityName by mutableStateOf("No active activity")

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
            Text(activeActivityName, fontSize = 50.sp, lineHeight = 55.sp)
            Text("10 minutes 13 seconds", fontSize = 30.sp, modifier = Modifier.padding(10.dp), color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold)
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)) {
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
                Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary)) {
                    AddIcon(iconType = Icons.Rounded.Check, description = "Complete", 30.dp)
                }
            }
        }
    }
}

@Composable
fun NewTaskPanel() {
    Surface(
        color = MaterialTheme.colorScheme.secondaryContainer,
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

            var isClicked by remember { mutableStateOf(false) }
            val openAlertDialog = remember { mutableStateOf(false) }


            Button(onClick = {
                isClicked = true
                openAlertDialog.value = true
            }, enabled = isEnabled) {
                Text("Start new activity named ${text.value}!")
            }

            if (isClicked) {
                if (activityRunning) {
                    when {
                        openAlertDialog.value -> {
                            AlertDialogExample(
                                onDismissRequest = { openAlertDialog.value = false },
                                onConfirmation = {
                                    openAlertDialog.value = false
                                    completedActivities.add(currentActivity)
                                    activeActivityName = text.value
                                    text.value = ""
                                    currentActivity = Activity(text.value, 0, false, LocalDateTime.now(), LocalDateTime.now(), UUID.randomUUID().toString())
                                },
                                dialogTitle = "Task already running",
                                dialogText = "Are you sure you want to cancel it and start this new one instead?",
                                icon = Icons.Default.Info
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Yes")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("No")
            }
        }
    )
}

@Composable
fun SuggestionsPanel() {
    Surface(
        color = MaterialTheme.colorScheme.secondaryContainer,
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
        color = MaterialTheme.colorScheme.primary,
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
        color = MaterialTheme.colorScheme.secondaryContainer,
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
        color = MaterialTheme.colorScheme.secondaryContainer,
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
        color = MaterialTheme.colorScheme.secondaryContainer,
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
