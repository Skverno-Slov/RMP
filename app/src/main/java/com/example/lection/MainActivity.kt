package com.example.lection

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lection.ui.theme.LectionTheme
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Greeting()
        }
    }
}

@Composable
fun Greeting() {
    LectionTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)
                .verticalScroll(rememberScrollState())) {
                val textSize = 30.sp
                val lang = listOf("Python", "C#", "Kotlin")
                lang.forEach {
                    Text(
                        text = it,
                        fontSize = textSize,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .background(Color.Blue)
                            .padding(5.dp)
                            .background(Color.Red, CircleShape)
                            .fillMaxWidth()
                            //.width(300.dp)
                            .height(100.dp)


                    )
                }

                for (l in lang){
                    Text(
                        text = l,
                        fontSize = textSize,
                    )
                }
                val hours = 18
                if (hours < 12){
                    Text("Доблого утличка")
                }
                else{
                    Text("Я вас категорически приветствую")
                }

                Box(modifier = Modifier
                    .sizeIn(30.dp, 30.dp)
                    .background(Color.Black)
                    .padding(5.dp)
                    .clickable{ }
                )

                ModifiedText()
            }
        }
    }
}

@Composable
fun ModifiedText(modifier: Modifier = Modifier){
    Text(text = "Привет",
        modifier = Modifier
            .background(Color.Red)
            .then(modifier))
}

fun getTime(): String{
    val calendar = Calendar.getInstance()
    val hours = calendar.get(Calendar.HOUR_OF_DAY)
    val minutes = calendar.get(Calendar.MINUTE)

    return "$hours:$minutes"
}


@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun GreetingPreview() {
    Greeting()
}