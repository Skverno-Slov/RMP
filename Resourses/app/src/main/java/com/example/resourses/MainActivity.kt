package com.example.resourses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.resourses.ui.theme.ResoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ResoursesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding).width(dimensionResource(R.dimen.header_width))) {
                        Text(stringResource(R.string.welcome), fontSize = 30.sp)
                        Text(stringResource(R.string.hello, "Zakhar", 18))
                        Button({}) {
                            Text(stringResource(R.string.login_button))
                        }

                        val font = FontFamily( Font(R.font.russo_one_regular, weight = FontWeight.Normal))

                        Text(pluralStringResource(R.plurals.cats, 1, 1))
                        Text(pluralStringResource(R.plurals.cats, 3, 3))
                        Text(pluralStringResource(R.plurals.cats, 15, 15))

                        Text("Hello", fontFamily = font)

                        Image(ImageBitmap.imageResource(R.drawable.image), "Котэ")
                        Image(ImageVector.vectorResource(R.drawable.picture), "Картинка", modifier = Modifier.size(200.dp))
                        Image(Icons.Outlined.Warning, "тревога", modifier = Modifier.size(200.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ResoursesTheme {
        Greeting("Android")
    }
}