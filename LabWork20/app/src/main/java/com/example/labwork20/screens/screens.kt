package com.example.labwork20.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController

@Composable
fun Authorize(onLoginClick: () -> Unit, onRegisterClick: () -> Unit){
    Column(Modifier.fillMaxSize()) {
        Text("Авториация", textAlign = TextAlign.Center)
        TextField("", onValueChange = {}, label = {
            Text("Логин")
        })
        TextField("", onValueChange = {}, label = {
            Text("Пароль")
        })
        Button({onLoginClick()}) {
            Text("Ок")
        }
        Button({onRegisterClick()}) {
            Text("Регистрация")
        }
    }
}

@Composable
fun Register(onBackLogin: () -> Unit){
    Column(Modifier.fillMaxSize()) {
        Text("Регистрация", textAlign = TextAlign.Center)
        TextField("", onValueChange = {}, label = {
            Text("Логин")
        })
        TextField("", onValueChange = {}, label = {
            Text("Пароль")
        })
        TextField("", onValueChange = {}, label = {
            Text("Подтвердить пароль")
        })
        Button({onBackLogin()}) {
            Text("Ок")
        }
    }
}

@Composable
fun ProductList(onProductClick: (String, String) -> Unit, products: List<Product>){
    LazyColumn() {
        item {
            Text("Список товаров")
        }
        items(products){ product ->
            Text(product.name, Modifier.clickable{onProductClick.invoke(product.name, product.price.toString())})
        }
    }
}

@Composable
fun ProductDetail(product: Product){
    Column {
        Text("Товар")
        Text("Название: ${product.name}")
        Text("Цена: ${product.price}")
    }
}

data class Product(val name: String, val price: Float)