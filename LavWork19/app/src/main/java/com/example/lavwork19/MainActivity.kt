package com.example.lavwork19

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lavwork19.ui.theme.LavWork19Theme
import kotlin.collections.List

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val images = listOf(
                R.drawable.apple,
                R.drawable.banana,
                R.drawable.bread,
                R.drawable.cabbage,
                R.drawable.coffe,
                R.drawable.cookies,
                R.drawable.cucumber,
                R.drawable.eggs,
                R.drawable.flour,
                R.drawable.gingerbread,
                R.drawable.juice,
                R.drawable.meat,
                R.drawable.milk,
                R.drawable.oil,
                R.drawable.porridge,
                R.drawable.sausage,
                R.drawable.shrimp,
                R.drawable.tea,
                R.drawable.tomato,
                R.drawable.water
            )
            var products = MutableList(20){Product(it, "Продукт${it+1}", 100.0 + it*10, images[it])}

            LavWork19Theme {
                Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
                    FloatingActionButton({}) { }
                }) { innerPadding ->
                    Column(Modifier.padding(innerPadding).fillMaxSize()) {
//                        ProductListRow(products)
                        ProductListColumn(products)
                    }
                }
            }
        }
    }
}

@Composable
fun CardListView(product: Product){
    Card(modifier = Modifier.padding(8.dp).fillMaxWidth(),
        border = BorderStroke(1.dp, Color.LightGray),
        colors = CardDefaults.cardColors(Color.Gray),
        elevation = CardDefaults.cardElevation(8.dp)) {
        Row(modifier = Modifier.padding(10.dp)) {
            Image(
                painterResource(product.ImageId), "",
                modifier = Modifier.size(75.dp)
            )
            Column (Modifier.padding(10.dp)){
            Text(product.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(product.price.toString(), fontSize = 14.sp)
            }
        }
    }
}

@Composable
fun CardView(product: Product){
    Card(modifier = Modifier.padding(8.dp).width(140.dp),
        border = BorderStroke(1.dp, Color.LightGray),
        colors = CardDefaults.cardColors(Color.Gray),
        elevation = CardDefaults.cardElevation(8.dp)) {
        Column(modifier = Modifier.padding(10.dp)
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painterResource(product.ImageId), "",
                modifier = Modifier.size(75.dp).clip(CircleShape)
            )
            Text(product.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(product.price.toString(), fontSize = 14.sp)
        }
    }
}

@Composable
fun ProductListRow(products: List<Product>){
    LazyRow(Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp)) {
        items(products) { product ->
            CardView(product)
        }
    }
}

@Composable
fun ProductListColumn(products: List<Product>){
    var selectedProduct by remember { mutableStateOf("") }

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val showButton by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }

    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp), state = listState) {
        stickyHeader(){
            Text(selectedProduct, Modifier.background(Color.White).height(30.dp).fillMaxSize())
        }
        items(products){ product ->
            Box(modifier = Modifier.clickable{selectedProduct = product.name}){
                CardListView(product)
            }
        }
    }
}

fun showButton(){

}
data class Product(val number: Int, val name: String, var price: Double, var ImageId: Int)