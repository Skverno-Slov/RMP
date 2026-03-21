package com.example.viewmodels

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viewmodels.ui.theme.ViewModelsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ViewModelsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(Modifier.padding(innerPadding).fillMaxSize()) {
                        CounterView()
                    }
                }
            }
        }
    }
}

@Composable
fun Main(vm: UserListViewModel = viewModel()){
    Column {


    }
}

@Composable
fun UserInput(userName: String, userAge: Int, changeName:(String) -> Int, changeAge:(String) -> Int, add: () -> Unit){
    Column {
        OutlinedTextField(value = userName, onValueChange = {changeName(it)})
        OutlinedTextField(value = userAge.toString(), onValueChange = {changeAge(it)})
        Button({add()}) {
            Text("Добавить")
        }
    }
}

@Composable
fun UserList(users: List<User>, delete:(User)-> Unit){
    LazyColumn(Modifier.fillMaxSize()) {
        items(users){u ->
            Text(u.name)
            Text(u.age.toString())
            TextButton({}) {
                Text("Удалить")
            }
        }
    }
}
@Composable
fun CounterView(vm: CounterViewModel = viewModel()){
    Button({vm.increment()}) {
        Text(vm.count.toString())
    }
}