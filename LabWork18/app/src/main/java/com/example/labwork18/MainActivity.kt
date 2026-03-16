package com.example.labwork18

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.labwork18.ui.theme.LabWork18Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabWork18Theme {
//                ProductsList()
//                Greeting(
//                    name = "Android",
//                    modifier = Modifier.padding(30.dp)
//                )
//                ProductDetail()
                ContactList()
                }
            }
        }
    }


@Composable
fun ProductsList(){
    var productCounter by remember { mutableStateOf(0) }
    var products = remember { listOf(Product(4, 10000F, "Котобулочка"),
        Product(3,  10000F, "Булочка с маком"),
        Product(6, 10000F, "Додеп"),
        Product(234,  10000F, "Штуки (поштушно)"),
        Product(4, 10000F, "Котобулочка2"),
        Product(4, 10000F, "Котобулочка3"),
        Product(4, 10000F, "Котобулочка4"),
        Product(4, 10000F, "Котобулочка5"),
        Product(4, 10000F, "Котобулочка6"),
        Product(4, 10000F, "Котобулочка7"))}

    Scaffold(modifier = Modifier.padding(30.dp),
        bottomBar = {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                Text("Товаров в корзине: $productCounter")
            }
        }) { innerPadding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(15.dp), contentPadding = PaddingValues(16.dp)) {
            items(products){ product ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column{
                        Text(product.name)
                        Text("Кол-л: ${product.count}")
                        Button({
                            productCounter++
                        }) {
                            Icon(Icons.Default.ShoppingCart, "")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ContactList(){
    var contacts = remember { List(100){i -> "просто чел$i"} }

    Scaffold(modifier = Modifier.padding(30.dp),
        floatingActionButton = {
            Row{
                FloatingActionButton({}) {
                    Icon(Icons.Filled.Call, "")
                }
                FloatingActionButton({}) {
                    Icon(Icons.Filled.Email, "")
                }
            }
        }) { innerPadding ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(innerPadding), contentPadding = PaddingValues(16.dp)) {
            items(contacts){ contact ->
                Card(modifier = Modifier.fillMaxWidth().clickable{}) {
                    Column{
                        Text(text = contact)
                    }
                }
            }
        }
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var counter by remember { mutableStateOf(0) }
    var color by remember { mutableStateOf(Color.Blue) }

    Column(modifier) {
        Text("Клики: $counter")

        Button({counter++}) {
            Text("Кнопка")
        }
        OutlinedButton({counter++}) {
            Text("Кнопка круче")
        }
        TextButton({counter++}) {
            Text("Кнопка нашла ларёк по продаже булочек с маком")
        }
        Column {
            Button({counter++}, shape = RoundedCornerShape(10.dp)) {
                Text("Кнопка спит")
            }
            Button({counter++}, shape = RoundedCornerShape(20.dp)) {
                Text("Кнопка плохо спит")
            }
            Button({counter++}, shape = RoundedCornerShape(40.dp)) {
                Text("Кнопка очень плохо спит")
            }
        }
        Button({
            counter++
            color = Color(Random.nextInt(256),Random.nextInt(256),Random.nextInt(256))
        }, colors = ButtonDefaults.buttonColors(containerColor = color)) {
            Text("Кнопка потеряла смысл слова 'магнитофон'")
        }
    }
}

@Composable
fun ProductDetail(){
    val product = Product(2,  10000F, "Picun F6")

    var quality by remember { mutableStateOf(1) }

    val totalPrice = quality * product.price

    Column(Modifier.fillMaxSize().padding(30.dp)) {
        Text("Название: ${product.name}   Цена:${product.price}")
        Row{
            IconButton({if (quality > 0) quality--}, enabled = quality > 0) {
                Icon(Icons.Filled.ArrowBack, "")
            }
            Text("Кол-л: $quality")
            IconButton({if (quality < 10) quality++}, enabled = quality < 10) {
                Icon(Icons.Filled.Add, "")
            }
        }

        Text("Итог: $totalPrice")
    }
}

data class Product(var count: Int, var price: Float, val name: String)