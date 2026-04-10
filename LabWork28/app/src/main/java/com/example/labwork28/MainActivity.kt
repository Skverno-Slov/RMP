package com.example.labwork28

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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.labwork28.ui.theme.LabWork28Theme
import java.time.Year

class MainActivity : ComponentActivity() {
    val dbHandler = DbHandler(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabWork28Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(Modifier.padding(innerPadding)) {
                        Main(dbHandler)
                    }
                }
            }
        }
    }
}

@Composable
fun Main(dbHandler: DbHandler){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "portal"){
        composable("portal") {
            PortalScreen({navController.navigate("add")}, {navController.navigate("list")})
        }
        composable("add"){
            AddBookScreen(dbHandler, {navController.popBackStack()})
        }
        composable("list"){
            ListBooksScreen(dbHandler, {navController.popBackStack()}, {id -> navController.navigate("update/$id")})
        }
        composable(
            route = "update/{id}",
            arguments = listOf(navArgument("id") {type = NavType.StringType})
        ){ backSateEntry ->
            val id = backSateEntry.arguments?.getString("id") ?: ""
            UpdateScreen(id = id, dbHandler, {navController.popBackStack()})
        }
    }
}

@Composable
fun PortalScreen(onAddClick: () -> Unit, onListClick: () -> Unit){
    Column {
        Button({ onAddClick() }) {
            Text("Добавить книгу")
        }
        Button({ onListClick() }) {
            Text("Список книг")
        }
    }
}

@Composable
fun UpdateScreen(id: String, dbHandler: DbHandler, onBack: () -> Unit){
    val books = dbHandler.getBooks()

    var book = books.find{it.id == id.toIntOrNull()}

    var title by remember { mutableStateOf(book?.name ?: "") }
    var author by remember { mutableStateOf(book?.author ?: "") }
    var year by remember { mutableStateOf(book?.year.toString() ?: "") }
    var pages by remember { mutableStateOf(book?.pageCount.toString() ?: "") }

    Column(Modifier.fillMaxSize()){
        OutlinedTextField(
            value = title,
            onValueChange = {title = it},
            label = {Text("Название")}
        )
        OutlinedTextField(
            value = author,
            onValueChange = {author = it},
            label = {Text("Автор")}
        )
        OutlinedTextField(
            value = year,
            onValueChange = {year = it},
            label = {Text("Год Издания")}
        )
        OutlinedTextField(
            value = pages,
            onValueChange = {pages = it},
            label = {Text("Количество Страниц")}
        )

        Button({
            val yearInt = year.toIntOrNull()
            val pagesInt = pages.toIntOrNull()

            if(title.isNotBlank() && author.isNotBlank() && yearInt != null && pagesInt != null){
                dbHandler.updateBook(Book(id = book?.id, name = title, author =  author, year =  yearInt, pageCount = pagesInt))
                onBack()
            }
        }) {
            Text("Сохранить")
        }

        Button({onBack()}) {
            Text("Отмена")
        }
    }
}

@Composable
fun ListBooksScreen(dbHandler: DbHandler, onBack: () -> Unit, onBookClick: (String) -> Unit){
    val books = dbHandler.getBooks()

    Column {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(books) { book ->
                Card(
                    Modifier.fillMaxWidth().padding(5.dp)
                ) {
                    Column(modifier = Modifier.clickable{onBookClick(book.id.toString())}) {
                        Text("Название: ${book.name}")
                        Text("Автор: ${book.author}")
                        Text("Год издания: ${book.year}")
                        Text("Количество страниц: ${book.pageCount}")
                    }
                }
            }
        }

        Button({ onBack() }) {
            Text("Назад")
        }
    }
}

@Composable
fun AddBookScreen(dbHandler: DbHandler, onBack: () -> Unit){
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var pages by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize()){
        OutlinedTextField(
            value = title,
            onValueChange = {title = it},
            label = {Text("Название")}
        )
        OutlinedTextField(
            value = author,
            onValueChange = {author = it},
            label = {Text("Автор")}
        )
        OutlinedTextField(
            value = year,
            onValueChange = {year = it},
            label = {Text("Год Издания")}
        )
        OutlinedTextField(
            value = pages,
            onValueChange = {pages = it},
            label = {Text("Количество Страниц")}
        )

        Button({
            val yearInt = year.toIntOrNull()
            val pagesInt = pages.toIntOrNull()

            if(title.isNotBlank() && author.isNotBlank() && yearInt != null && pagesInt != null){
                dbHandler.addBook(Book(name = title, author =  author, year =  yearInt, pageCount = pagesInt))
                onBack()
            }
        }) {
            Text("Сохранить")
        }

        Button({onBack()}) {
            Text("Отмена")
        }
    }
}