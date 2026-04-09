package com.example.labwork27

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.labwork27.services.User
import com.example.labwork27.ui.theme.LabWork27Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabWork27Theme {
                Scaffold(Modifier.fillMaxSize()) {innerPadding->
                    Column(Modifier.padding(innerPadding)) {
                        Main()
                    }
                }
            }
        }
    }
}

@Composable
fun Main(vm: UserViewModel = viewModel()){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "reg"){
        composable("reg") {
            Column(Modifier.padding(10.dp).fillMaxWidth()) {
                Text("Регистрация", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                RegisterScreen(vm, {navController.navigate("auth")})
            }
        }
        composable("auth"){
            Column(Modifier.padding(10.dp).fillMaxWidth()) {
                Text("Авторизация", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                AuthorizationScreen(
                    vm,
                    { navController.navigate("reg") },
                    { login -> navController.navigate("profile/$login") })
            }
        }
        composable(
            route = "profile/{login}",
            arguments = listOf(navArgument("login") {type = NavType.StringType})
        ){ backSateEntry ->
            val login = backSateEntry.arguments?.getString("login") ?: ""
            Column(Modifier.padding(10.dp).fillMaxWidth()) {
                Text(text = "Профиль", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                ProfileScreen(
                    login = login,
                    vm = vm,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}

@Composable
fun RegisterScreen(vm: UserViewModel, onNav: () -> Unit){
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var age by remember { mutableIntStateOf(12) }

    var message by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(
            value = login,
            onValueChange = { login = it },
            label = { Text("Логин") }
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Пароль") }
        )

        OutlinedTextField(
            value = age.toString(),
            onValueChange = { input ->
                var value = input.toIntOrNull()
                if (value != null) {
                    age = value
                }
            },
            label = { Text("Возраст") }
        )

        Button({
            if(!login.isEmpty() && !password.isEmpty() && !age.toString().isEmpty()){
                vm.registerUser(User(login, password, age, ""))
                message = "Успешно"
            }
        }) {
            Text("Зарегистрироваться")
        }

        Button({onNav()}) {
            Text("Авторизация")
        }

        Text(message)
    }
}

@Composable
fun AuthorizationScreen(vm: UserViewModel = viewModel(), onNav: () -> Unit, onLoginClick: (String) -> Unit){
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column {
        OutlinedTextField(
            value = login,
            onValueChange = { login = it },
            label = { Text("Логин") }
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Пароль") }
        )

        Button({
            if(!login.isEmpty() && !password.isEmpty()){
                if(vm.authUser(User(login, password, 1, ""))){
                    onLoginClick(login)
                }
                else{
                    password = ""
                }
            }
        }) {
            Text("Войти")
        }

        Button({onNav()}) {
            Text("Регистрация")
        }
    }
}

@Composable
fun ProfileScreen(vm: UserViewModel, login: String, onBack: () -> Unit){
    val user = vm.getUser(login)
    var about by remember { mutableStateOf(user?.about) }

    Column {
        Text("Логин: ${user?.login}")
        Text("Возраст: ${user?.age}")

        OutlinedTextField(
            value = about!!,
            onValueChange = { about = it },
            label = { Text("О себе") }
        )


        Button({ vm.updateUser(user, about) }) {
            Text("Сохранить изменения")
        }

        Button({ onBack() }) {
            Text("Назад")
        }
    }
}