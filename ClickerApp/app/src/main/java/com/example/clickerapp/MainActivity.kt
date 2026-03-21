package com.example.clickerapp

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.clickerapp.ui.theme.ClickerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClickerGame()
        }
    }
}

@Composable
fun ClickerGame(vm: GameViewModel = viewModel()){
    ClickerAppTheme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                Column(Modifier.fillMaxWidth()
                    .statusBarsPadding()
                    .height(100.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "ДУШ ПРЕНЕСЕНО В ЖУРТВУ", textAlign = TextAlign.Center, fontSize = 30.sp, color = MaterialTheme.colorScheme.onPrimaryContainer)
                    Text(vm.score.toString(), textAlign = TextAlign.Center, fontSize = 30.sp, color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
            }) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)){
                GameScreen(vm)
            }
        }
    }
}

@Composable
fun GameScreen(vm: GameViewModel) {
    Box(Modifier.fillMaxSize()){
        val particles = remember { mutableStateListOf<Particle>() }
        Box(Modifier.size(300.dp).clip(CircleShape).align(Alignment.Center).clickable{
            vm.onTap()
            repeat(5){
                particles.add(Particle(50f, 50f))
            }
        }){
            Image(painterResource(R.drawable.cthulhu_star), "Background", Modifier.fillMaxSize())
            Image(painterResource(R.drawable.cthulhu), "Ctuluhu", Modifier.fillMaxSize(0.7f).align(Alignment.Center))
        }
        ParticleAnimation(particles)
    }
}

