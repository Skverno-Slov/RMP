package com.example.animationlectin

import android.animation.Keyframe
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animationlectin.ui.theme.AnimationLectinTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimationLectinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(Modifier.padding(innerPadding)) {
                        ColorAnimationExample()
                        OffsetAnimationExample()
                        RoteteAnimationExample()
                        ScaleAnimationExample()
                    }
                }
            }
        }
    }
}

@Composable
fun ColorAnimationExample(){
    var isBlue by remember { mutableStateOf(false) }

    val color by animateColorAsState(
        targetValue = if(isBlue) Color.Blue else Color.Red,
        animationSpec = tween ( 1000 )
    )

    Box(
        Modifier.size(200.dp)
            .background(color)
            .clickable{isBlue=!isBlue}
    )
}

@Composable
fun OffsetAnimationExample(){
    var moved by remember { mutableStateOf(false) }

    val offsetX by animateDpAsState(
        targetValue = if(moved) 200.dp else 100.dp,
        animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy)
    )

    Box(
        Modifier
            .size(offsetX)
            .offset(x = offsetX)
            .background(Color.Green)
            .clickable{moved=!moved}
    )
}

@Composable
fun RoteteAnimationExample(){
    var anomatedOffset = remember {
        Animatable(
        initialValue = Offset.Zero,
            typeConverter = Offset.VectorConverter)}

    Box(
        Modifier.fillMaxSize().background(Color.LightGray)
            .pointerInput(Unit){
                coroutineScope {
                    while (true){
                        val offset = awaitPointerEventScope { awaitFirstDown().position }
                        launch {
                            anomatedOffset.animateTo(
                                offset,
                                animationSpec = spring(stiffness = Spring.StiffnessLow)
                            )
                        }
                    }
                }
            }
    )
}

@Composable
fun ScaleAnimationExample(){
    var scaled by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if(scaled) 2f else 0.5f,
        animationSpec = keyframes { durationMillis = 1000
                if(scaled){
                    0.6f at 100
                    1.5f at 500
                    1f at 900
                }
        }
    )

    Box(
        Modifier
            .size(200.dp)
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .background(Color.Gray)
            .clickable{scaled=!scaled}
    )
}

@Composable
fun AnimatableExample(){
    var animatedOffset by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if(scaled) 2f else 0.5f,
        animationSpec = keyframes { durationMillis = 1000
            if(scaled){
                0.6f at 100
                1.5f at 500
                1f at 900
            }
        }
    )

    Box(
        Modifier
            .size(200.dp)
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .background(Color.Gray)
            .clickable{scaled=!scaled}
    )
}