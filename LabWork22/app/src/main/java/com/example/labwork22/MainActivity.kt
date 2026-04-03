package com.example.labwork22

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.labwork22.ui.theme.LabWork22Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabWork22Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(Modifier.padding(innerPadding)) {
//                        DownloadScreen()
//                        LinearDownloadScreen()
//                        CountdownTimerScreen()
                    }
                }
                MessageBadgeScreen()
            }
        }
    }
}

@Composable
fun DownloadScreen() {
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(isLoading) {
        if (isLoading) {
            delay(10000)
            isLoading = false
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(50.dp).padding(5.dp),
                color = MaterialTheme.colorScheme.primary
            )
        } else {
            Text("ГЛАВНОЕ МЕНЮ")
        }

        Button(
            onClick = { isLoading = true },
            enabled = !isLoading
        ) {
            Text(if (isLoading) "Загрузка..." else "Скачать")
        }
    }
}

@Composable
fun LinearDownloadScreen() {
    var progress by remember { mutableStateOf(0f) }
    var isDownloading by remember { mutableStateOf(false) }

    LaunchedEffect(isDownloading) {
        if (isDownloading) {
            progress = 0f
            while (progress < 1f) {
                delay(100)
                val step = Random.nextFloat() * 0.05f
                progress = if (progress + step > 1f) 1f else progress + step
            }
            isDownloading = false
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Загружено: ${(progress * 100).toInt()}%"
        )

        CustomLinearProgress(progress, Color.Blue)
        CustomLinearProgress(progress, Color.Red)
        CustomLinearProgress(progress, Color.Green)

        Button(
            onClick = { isDownloading = true },
            enabled = !isDownloading
        ) {
            Text("Скачать")
        }
    }
}

@Composable
fun CustomLinearProgress(progress: Float, color: Color) {
    LinearProgressIndicator(
        progress = { progress },
        modifier = Modifier.fillMaxWidth().height(8.dp).padding(2.dp),
        color = color,
        trackColor = Color.White
    )
}

@Composable
fun CountdownTimerScreen() {
    var timeLeft by remember { mutableIntStateOf(60) }
    var isRunning by remember { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        if (isRunning) {
            while (timeLeft > 0) {
                delay(1000)
                timeLeft--
            }
            isRunning = false
        }
    }

    val indicatorColor = when {
        timeLeft >= 30 -> {
            Color.Green
        }
        timeLeft >= 10 -> {
            Color.Yellow
        }
        else -> {
            Color.Red
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                progress = { timeLeft / 60f },
                modifier = Modifier.size(150.dp),
                color = indicatorColor,
                strokeWidth = 12.dp
            )

            Text(
                text = "$timeLeft"
            )
        }


        Button(
            onClick = {
                if (timeLeft == 0) timeLeft = 60
                isRunning = true
            },
            enabled = !isRunning
        ) {
            Text("Начать")
        }
    }
}

@Composable
fun MessageBadgeScreen() {
    var messageCount by remember { mutableIntStateOf(0) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BadgedBox(
                badge = {
                    if (messageCount > 0) {
                        Badge(
                            containerColor = Color.Red,
                            contentColor = Color.White
                        ) {
                            Text("$messageCount")
                        }
                    }
                }
            ) {
                IconButton(onClick = {
                    messageCount = 0
                    scope.launch {
                        snackbarHostState.showSnackbar("Все сообщения прочитаны")
                    }
                }) {
                    Icon(
                        Icons.Default.Email,
                        ""
                    )
                }
            }

            Button(onClick = {
                messageCount += Random.nextInt(1, 10)
            }) {
                Text("Обновить")
            }
        }
    }
}