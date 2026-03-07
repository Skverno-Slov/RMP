package com.example.labwork15

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.labwork15.ui.theme.LabWork15Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabWork15Theme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation(){
    var current by remember { mutableStateOf(Screens.Reg) }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(Modifier.fillMaxSize().padding(innerPadding)) {
            NavBar({current = Screens.Reg}, {current = Screens.Auth}, {current = Screens.Prof})
            when(current){
                Screens.Reg -> screens.RegistrationScreen()
                Screens.Prof -> screens.ProfileScreen()
                Screens.Auth -> screens.AuthorizationScreen()
            }
        }
    }
}

@Composable
fun NavBar(onClickAuth: () -> Unit, onClickReg: () -> Unit, onClickProfile: () -> Unit){
    Row(Modifier.fillMaxWidth()){
        Button({onClickAuth()}) {
            Text("Авторизация")
        }
        Button({onClickReg()}) {
            Text("Регистрвция")
        }
        Button({onClickProfile()}) {
            Text("Профиль")
        }
    }
}

enum class Screens{
    Auth,
    Reg,
    Prof
}