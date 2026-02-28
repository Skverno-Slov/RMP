package com.example.statelectionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.statelectionapp.ui.theme.StateLectionAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var state by rememberSaveable { mutableStateOf("Hello") }
            StateLectionAppTheme() {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        TextField(state, {state= it})
                        Text(state)

                        Counter()
                        LoginScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun Counter(){
    var clicks by remember { mutableStateOf(0) }
    val onClicksChange = {value: Int -> clicks = value}

    Column()
    {
        Text(text = clicks.toString())
        Increment(clicks, onClicksChange)
    }
}

/**
 * **Комментарии** кнопки инкремента
 *
 * @param clicks количество нажатий
 */
@Composable
fun Increment(clicks: Int, onClicksChange: (Int) -> Unit){
    Button({
        onClicksChange(clicks + 1)
    })
    {
        Text("+")
    }
}

@Composable
fun LoginScreen(){
    var login by remember {mutableStateOf("")}
    var password by remember {mutableStateOf("")}
    var showMessage by remember { mutableStateOf(false) }

    if(showMessage) {
        AlertDialog({ showMessage = false }, confirmButton = {
            Button({ showMessage = false }) {
                Text("Ok")
            }
        }, text = {Text("$login авторизован")})
    }

    Column {
        OutlinedTextField(login, { login = it }, label = {Text("Логин")})
        OutlinedTextField(password, { password = it }, label = {Text("Пароль")})
        Button({showMessage = true})
        {
            Text("Войти")
        }
    }
}