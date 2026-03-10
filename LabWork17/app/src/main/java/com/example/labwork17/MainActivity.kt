package com.example.labwork17

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.labwork17.ui.theme.LabWork17Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabWork17Theme {
                val scrollState = rememberScrollState()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding).verticalScroll(scrollState))
                    {
                        var login by remember { mutableStateOf("") }
                        var password by remember { mutableStateOf("") }
                        var confirm by remember { mutableStateOf("") }
                        var phone by remember { mutableStateOf("") }
                        var email by remember { mutableStateOf("") }
                        var age by remember { mutableStateOf("") }
                        var site by remember { mutableStateOf("") }
                        var resultText by remember { mutableStateOf("") }
                        var allowPersonalData by remember { mutableStateOf(false) }

                        OutlinedTextField(login, {login = it},
                            label = {Text("Логин")},
                            placeholder = {Text("login")},
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                        )
                        OutlinedTextField(password, {password = it},
                            label = {Text("Пароль")},
                            placeholder = {Text("Password")},
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                        )
                        OutlinedTextField(confirm, {confirm = it},
                            label = {Text("Подтверждение пароля")},
                            placeholder = {Text("password")},
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                        )
                        OutlinedTextField(phone, {phone = it},
                            label = {Text("Телефон")},
                            placeholder = {Text("+7")},
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                        )
                        OutlinedTextField(email, {email = it},
                            label = {Text("Email")},
                            placeholder = {Text("mail@example.com")},
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                        )
                        OutlinedTextField(age, {age = it},
                            label = {Text("Возраст")},
                            placeholder = {Text("18")},
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        OutlinedTextField(site, {site = it},
                            label = {Text("Персональный сайт")},
                            placeholder = {Text("https://...")},
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri)
                        )
                        Row{
                            Checkbox(checked = allowPersonalData, onCheckedChange = {allowPersonalData = it})
                            Text("Я согласен на обработку персональных данных")
                        }



                        Button(onClick = {
                            if(login == "" || password == "" || confirm == "" || phone == "" || email == "" || age == "" || site == ""){
                                resultText = "Не все поля заполнены"

                            }
                            else{
                            resultText = "$login, успешно зарегистрирован"
                            }
                        }, enabled = allowPersonalData) {
                            Text("Зарегистрироваться")
                        }
                        if(resultText != ""){
                            ShowMessage(resultText)
                        }

                        val correctCode = "1134"
                        var code by remember { mutableStateOf("") }
                        var attempts by remember { mutableStateOf(3) }
                        var result by remember { mutableStateOf("") }

                        OutlinedTextField(code, { newCode ->
                            if (newCode.length < 5) code = newCode else code},
                            label = {Text("Код")},
                            placeholder = {Text("1234")},
                            enabled = attempts > 0,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
                        )
                        Button(onClick = {
                            if(code != correctCode){
                                result = "Не верно"
                                attempts--
                            }
                            else{
                                result = "Верно"
                            }
                        }, enabled = attempts > 0) {
                            Text("Отправить")
                        }
                        if(result != ""){
                            ShowMessage(result)
                        }


                        val colors = listOf(
                            "Чёрный" to Color.Black,
                            "Жёлтый" to Color.Yellow,
                            "Серый" to Color.Gray,
                            "Зелёный" to Color.Green,
                            "Красный" to Color.Red,
                        )
                        var selectedColor by remember { mutableStateOf(colors[0]) }

                        Text("МЕГАКРУТО", color = selectedColor.second)
                        colors.forEach { option ->
                        Row(Modifier.selectable(selected = (option == selectedColor),
                            onClick =  {selectedColor = option})) {
                            RadioButton(selected = (option == selectedColor),
                                onClick = {selectedColor = option})
                            Text(option.first)
                            Box(Modifier.background(option.second).size(20.dp))
                        }}
                    }
                }
            }
        }
    }
}

@Composable
fun ShowMessage(messge: String){
    Text("$messge")
}
