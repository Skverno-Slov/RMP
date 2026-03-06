package com.example.labwork14

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.labwork14.ui.theme.LabWork14Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabWork14Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

/**
 * Делит 2 числа
 * @param a Делимое
 * @param b Делитель
 * @return Результат деления переданых чисел
 * @throws ArithmeticException Деление на ноль
 * @sample com.example.labwork14.exampleSum
 * @since 1.0
 * @author Разработчик из мха
 */
fun div(a: Double, b: Double): Double {
    if (b == 0.0){
        throw ArithmeticException("Деление на ноль")
    }
    return a / b
}

/**
 * Отрисовывает стандарнтый интерфейс
 * @param name строка для подстановки в текст
 * @param modifier Модификаторы для текста
 * @sample com.example.labwork14.GreetingPreview
 * @since 1.0
 * @author Разработчик из мха
 */
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

/**
 * Предпросмотр стандартного интерфейса
 * @since 1.0
 * @author Разработчик из мха
 */
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LabWork14Theme {
        Greeting("Android")
    }
}

fun exampleUser(){
    var user = User()
    if (user.isPasswordCorrect("123")){
        //Если пароль верный
    } else{
        //Если пароль не верный
    }
}

fun exampleSum(){
    var result = div(4.0, 2.0)
}

/**
 * Класс для хранения логина и пароля пользователя
 * @property login Логин пользователя
 * @property password Пароль пользователя
 * @sample com.example.labwork14.exampleUser
 * @since 1.0
 * @author Разработчик из мха
 */
public data class User(var login: String = "User", var password: String = "123"){
    /**
     * Проверка на корректность пароля
     * @param inputPassword Пароль для проверки
     * @sample com.example.labwork14.exampleUser
     * @since 1.0
     * @author Разработчик из мха
     */
    public fun isPasswordCorrect(inputPassword: String): Boolean {
        return inputPassword == password
    }
}