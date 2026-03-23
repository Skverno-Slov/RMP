package com.example.labwork21

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.labwork21.ui.theme.LabWork21Theme
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabWork21Theme {
//                Login()
                MemoryManager()
//                Game()
            }
        }
    }
}

@Composable
fun Login(){
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        var login by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var dialogType by remember { mutableStateOf(0) }
        Column(Modifier.padding(innerPadding)) {
            TextField(login, {login = it}, label = {Text("Логин")})
            TextField(password, {password = it}, label = {Text("Пароль")})
            Button({
                dialogType = if(login == "" || password == "") 2 else 1
            }) {
                Text("Войти")
            }
            if(dialogType == 2){
                AlertDialog(
                    onDismissRequest = {dialogType = 0},
                    containerColor = Color.LightGray,
                    title = {Text("Ошибка входа", color = Color.Red)},
                    text = {Text("Заполните поля: ${if(login != "") "" else "Логин"} ${if(password != "") "" else "Пароль"}", color = Color.Red)},
                    confirmButton = {Button({dialogType = 0}, colors = ButtonDefaults.buttonColors(containerColor = Color.Green)) {
                        Row {
                            Icon(Icons.Default.Check, "Ok")
                            Text("Ok")
                        }
                    }},
                    dismissButton = {Button({dialogType = 0}, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)){
                        Row {
                            Icon(Icons.Default.Clear, "Ok")
                            Text("Cancel")
                        }
                    }
                    }
                )
            }

            if(dialogType == 1){
                AlertDialog(
                    onDismissRequest = {dialogType = 0},
                    title = {Text("Успех", color = Color.Green)},
                    text = {Text("Добро пожаловать, $login", color = Color.Green)},
                    confirmButton = {Button({dialogType = 0}, colors = ButtonDefaults.buttonColors(containerColor = Color.Green)) {
                        Row {
                            Icon(Icons.Default.Check, "Ok")
                            Text("Ok")
                        }
                    }}
                )
            }
        }
    }
}

@Composable
fun MemoryManager() {
    var memory by remember { mutableStateOf(Random.nextInt(8000)) }
    var snackbarHostState = remember { SnackbarHostState() }
    var corutineScope = rememberCoroutineScope()
    Scaffold(modifier = Modifier.fillMaxSize(), snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            Text("$memory МБ")
            Button({
                memory = 0
                corutineScope.launch {
                    snackbarHostState.showSnackbar(
                        "Кэш очищен",
                        duration = SnackbarDuration.Short
                    )
                }
            }) {
                Text("Очистить кэш")
            }
        }
    }
}

@Composable
fun Game(){
    var isGameStarted by remember { mutableStateOf(false) }
    var snackbarHostState = remember { SnackbarHostState() }
    var corutineScope = rememberCoroutineScope()
    Scaffold(modifier = Modifier.fillMaxSize(), snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            if(isGameStarted){
                Text("Вы вошли в игру")
                Image(
                    painter = painterResource(R.drawable.image),
                    "image",
                    Modifier.size(400.dp)
                )
            }
            else {
                Button({
                    corutineScope.launch {
                        val result = snackbarHostState.showSnackbar(
                            "Загрузить дополнительные ресурсы?",
                            actionLabel = "Ok",
                            withDismissAction = true,
                            duration = SnackbarDuration.Indefinite
                        )

                        when(result){
                            SnackbarResult.ActionPerformed -> {
                                isGameStarted = true
                            }
                            SnackbarResult.Dismissed -> {}
                        }
                    }
                }) {
                    Text("Играть")
                }
            }
        }
    }
}