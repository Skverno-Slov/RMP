package com.example.navigtionlection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.navigtionlection.ui.theme.NavigtionLectionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavRoot()

        }
    }
}

@Composable
fun TrueNavRoot(){
    val navController = rememberNavController()
    NavigtionLectionTheme() { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            NavHost(navController, startDestination =  )
        }
    }
}


@Composable
fun NavRoot(){
    var current by remember { mutableStateOf("home") }

    NavigtionLectionTheme(){
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(Modifier.padding(innerPadding)) {
                NavBar({current = "home"}, {current = "profile"}, {current = "settings"})
                when(current){
                    "home" -> Home()
                    "profile" -> Profile()
                    "settings" -> Settings()
                }
            }
        }
    }
}

@Composable
fun NavBar(onClickHome: () -> Unit, onClickProfile: () -> Unit, onClickSettings: () -> Unit){
    Row(Modifier.fillMaxSize()){
        Button({onClickHome()}) {
            Text("Main")
        }
        Button({onClickProfile()}) {
            Text("Profile")
        }
        Button({onClickSettings()}) {
            Text("Settings")
        }
    }
}

@Composable
fun Home(){
    Text("Main")
}

@Composable
fun Profile(){
    Text("Profile")
}

@Composable
fun Settings(){
    Text("settings")
}