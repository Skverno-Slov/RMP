package com.example.labwork13

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Label
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.labwork13.screns.LoginScreen
import com.example.labwork13.ui.theme.LabWork13Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabWork13Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "ispp31",
                        modifier = Modifier.padding(innerPadding)
                    )
                    LoginScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center) {
        Text(
            text = "Привет, $name!",
            fontSize = 24.sp,
            color = Color.Red,
            modifier = modifier
        )
    }
}

//task2
@Preview(showBackground = true)
@Composable
fun Task2() {
    Text("Добро пожаловать")
    Button({})
    {
        Text("OK")
    }
}

@Preview(showBackground = true)
@Composable
fun Task2Column(){
    Column {
        Text("Добро пожаловать")
        Button({})
        {
            Text("OK")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Task2Row(){
    Row {
        Text("Добро пожаловать")
        Button({})
        {
            Text("OK")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Task2Box(){
    Box {
        Text("Добро пожаловать")
        Button({})
        {
            Text("OK")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Task3Box(){
    Box(Modifier.size(300.dp), contentAlignment = Alignment.Center){
        Box(modifier = Modifier.align(Alignment.TopStart).background(Color.Red).size(50.dp))
        Box(modifier = Modifier.align(Alignment.TopCenter).background(Color.Yellow).size(50.dp))
        Box(modifier = Modifier.align(Alignment.TopEnd).background(Color.Blue).size(50.dp))
        Box(modifier = Modifier.align(Alignment.CenterStart).background(Color.Green).size(50.dp))
        Box(modifier = Modifier.align(Alignment.CenterEnd).background(Color.Magenta).size(50.dp))
        Box(modifier = Modifier.align(Alignment.BottomStart).background(Color.Cyan).size(50.dp))
        Box(modifier = Modifier.align(Alignment.BottomCenter).background(Color.DarkGray).size(50.dp))
        Box(modifier = Modifier.align(Alignment.BottomEnd).background(Color.Gray).size(50.dp))
        Box(modifier = Modifier.align(Alignment.Center).background(Color.Black).size(250.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun Task3Column(){
    Column(Modifier.size(300.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
        Text("5%", modifier = Modifier.background(Color.Green).fillMaxSize().weight(5f))
        Text("15%", modifier = Modifier.background(Color.White).fillMaxSize().weight(15f))
        Text("30%", modifier = Modifier.background(Color.Blue).fillMaxSize().weight(30f))
        Text("50% ZZZ", modifier = Modifier.background(Color.Red).fillMaxSize().weight(50f))
    }
}

@Preview(showBackground = true)
@Composable
fun Task5(){
    var list = listOf("Цыплёнок", "гусь", "куропатка", "курица", "петух(опущенный)")
    LazyColumn(Modifier.size(300.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {
        itemsIndexed(list) { i, v ->
            Text(text = v, modifier = Modifier.padding(3.dp).background(Color.LightGray), textAlign = TextAlign.Center)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LabWork13Theme {
        Greeting("ispp31")
    }
}