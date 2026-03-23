package com.example.clickerapp

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.content.MediaType.Companion.Image
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.clickerapp.ui.theme.ClickerAppTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClickerGame()
        }
    }
}

data class PageData(val name: String, val icon: ImageVector)

@Composable
fun ClickerGame(vm: GameViewModel = viewModel()){
    val pageStaet = rememberPagerState() { 2 }
    val pages = remember { mutableStateMapOf(0 to PageData("Main", Icons.Default.Home),
        1 to PageData("Shop", Icons.Default.ShoppingCart)) }
    val corutineScope = rememberCoroutineScope()
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
            }, bottomBar = {
                Row(Modifier.fillMaxWidth()
                    .statusBarsPadding()
                    .height(100.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                    verticalAlignment = Alignment.CenterVertically) {
                    pages.forEach { (n, page) ->
                        Button(onClick = {corutineScope.launch {
                            pageStaet.animateScrollToPage(n, animationSpec = tween(300))
                        }}, Modifier.weight(1f).fillMaxHeight(),
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(contentColor = if(pageStaet.currentPage == n) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.onPrimaryContainer)
                        ) {
                            Icon(page.icon, page.name)
                        }
                    }
                }
            }) { innerPadding ->
            HorizontalPager(pageStaet, Modifier.padding(innerPadding)){ page->
                when(page){
                    0 -> GameScreen(vm)
                    1 -> ShopScreen(vm)
                }
            }
        }
    }
}

@Composable
fun ShopScreen(vm: GameViewModel){
    Column(Modifier.fillMaxSize()) {
        Button({}) {
            Text("Кнопка1")
        }
        Button({}) {
            Text("Кнопка2")
        }
        Button({}) {
            Text("Кнопка3")
        }
    }
}

@Composable
fun GameScreen(vm: GameViewModel) {
    Box(Modifier.fillMaxSize()){
        var buttonPosition by remember { mutableStateOf(Offset.Zero) }
        val particles = remember { mutableStateListOf<Particle>() }

        var isPressed by remember { mutableStateOf(false) }
        val scale by animateFloatAsState(
            if(isPressed) 0.95f else 1f,
            animationSpec = tween ( 100 )
        )

        Box(Modifier.size(300.dp).clip(CircleShape).align(Alignment.Center)
            .onGloballyPositioned{
                buttonPosition = it.positionInParent()
            }
            .pointerInput(Unit){
            coroutineScope {
                while (true){
                    awaitPointerEventScope {
                        val down = awaitFirstDown()
                        val pos = down.position+buttonPosition
                        isPressed = true
                        vm.onTap()
                        repeat(5){
                            particles.add(Particle(pos.x, pos.y))
                        }
                        down.consume()
                        val up = waitForUpOrCancellation()
                        if(up!=null){
                            isPressed = false
                        }
                    }
                }
            }
        }){
            Image(painterResource(R.drawable.cthulhu_star), "Background", Modifier.fillMaxSize())
            Image(painterResource(R.drawable.cthulhu), "Ctuluhu", Modifier.graphicsLayer(scaleX = scale, scaleY = scale).fillMaxSize(0.7f).align(Alignment.Center))
        }
        ParticleAnimation(particles)
    }
}

