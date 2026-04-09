package com.example.labwork26

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.labwork26.ui.theme.LabWork26Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabWork26Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
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
    NavHost(navController = navController, startDestination = "list"){
        composable("list") {
            UserList(vm, {login -> navController.navigate("details/$login")})
        }
        composable(
            route = "details/{login}",
            arguments = listOf(navArgument("login") {type = NavType.StringType})
        ){ backSateEntry ->
            val login = backSateEntry.arguments?.getString("login") ?: ""
            UserDetail(
                login = login,
                vm = vm,
                onBack = {navController.popBackStack()}
            )
        }
    }
}

@Composable
fun UserList(vm: UserViewModel, onUserClick: (String) -> Unit){
    Column() {
        OutlinedTextField(
            value = vm.login,
            onValueChange = {vm.login = it},
            label = {Text("Логин")}
        )

        OutlinedTextField(
            value = vm.password,
            onValueChange = {vm.password = it},
            label = {Text("Пароль")}
        )

        OutlinedTextField(
            value = vm.email,
            onValueChange = {vm.email = it},
            label = {Text("Почта")}
        )

        Button({
            if(vm.login.isNotBlank() || vm.password.isNotBlank() || vm.email.isNotBlank()){
                vm.addUser()
            }
        }) {
            Text("Добавить")
        }

        LazyColumn() {
            items(vm.users) {user ->
                Text("Логин: ${user.login} | Пароль: ${user.password} | Почта: ${user.email}",
                    Modifier.clickable{onUserClick(user.login)})
            }
        }
    }
}

@Composable
fun UserDetail(login: String, vm: UserViewModel, onBack: () -> Unit){
    val user = vm.getUserByLogin(login)

    var password by remember { mutableStateOf(user?.password ?: "") }
    var email by remember { mutableStateOf(user?.email ?: "") }

    Column() {
        Button({onBack()}) {
            Text("Назад")
        }
        if(user!= null){
            Text("Логин: ${user.login}")

            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                label = {Text("Пароль")}
            )

            OutlinedTextField(
                value = email,
                onValueChange = {email = it},
                label = {Text("Почта")}
            )

            IconButton({
                vm.updateUser(user.login, password, email)}) {
                Icon(Icons.Default.Create, "Сохранить")
            }
        }

    }
}
