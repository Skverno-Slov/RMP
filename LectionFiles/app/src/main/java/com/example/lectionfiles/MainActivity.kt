package com.example.lectionfiles

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.lectionfiles.ui.theme.LectionFilesTheme
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LectionFilesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(modifier) {
        val context = LocalContext.current

        context.filesDir?.let { Text(it.path) }
        context.getDir("custom", 0)?.let { Text(it.path) }

        val file = File(context.filesDir, "exmple.txt")

        file.writeText("Привет из файла")

        val text = if(file.exists()) file.readText() else ""

        Text(text)

        val eula = context.assets.open("sample.txt").bufferedReader().use { it.readText() }

        Text(eula)

        var path by remember { mutableStateOf<Uri?>(null) }

        var launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
            path = uri
        }

        Button({launcher.launch("image/*")}) {
            Text("Выбрать")
        }

        Text(path.toString())
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LectionFilesTheme {
        Greeting("Android")
    }
}