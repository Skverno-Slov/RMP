package screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun AuthorizationScreen(){
    Column(Modifier.fillMaxSize()) {
        Text("Авторизация")
        TextField( "", {},
            label = {Text("Логин")})
        TextField( "", {},
            label = {Text("Пароль")})
        Button({}) {
            Text("Войти")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationScreen(){
    Column(Modifier.fillMaxSize()) {
        Text("Регистрация")
        TextField( "", {},
            label = {Text("Логин")})
        TextField( "", {},
            label = {Text("Пароль")})
        TextField( "", {},
            label = {Text("Подтверждение пароля")})
        TextField( "", {},
            label = {Text("Телефон")})
        TextField( "", {},
            label = {Text("Эл. почта")})
        TextField( "", {},
            label = {Text("Возраст")})
        Button({}) {
            Text("Ок")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreen(){
    Column(Modifier.fillMaxSize()) {
        Text("Профиль")

        Text("Логин:")
        Text("Имя:")
        Text("Возраст:")
        Text("Эл.почта:")
        Text("О себе:")

        Button({}) {
            Text("Назад")
        }
    }
}