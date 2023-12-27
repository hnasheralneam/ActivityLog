package com.example.activitylog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
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
                                modifier = Modifier.fillMaxSize()
                                    .padding(
                                        20.dp
                                    )
                            ) {
                                Text("Activity name")
                                Text("Activity time running")
                                Row {
                                    Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)) {
                                        Text("cancel")
                                    }
                                    Button(onClick = { /*TODO*/ }) {
                                        Text("play/pause")
                                    }
                                    Button(onClick = { /*TODO*/ }) {
                                        Text("complete")
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text("start new activity")
                            Text("suggestions")
                            Text("stats")
                            Text("history")
                            Text("settings")
                            Text("GNUv3 copyright by Hamza Nasher-Alneam, code on GitHub. Last edit Dec 26 2023")
                        }
                    }
                }
            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "$name!",
//        modifier = modifier
//    )
//}
