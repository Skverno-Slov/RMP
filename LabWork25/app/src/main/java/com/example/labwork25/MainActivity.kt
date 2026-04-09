package com.example.labwork25

import android.R
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme{
                    var tabIndex by remember { mutableStateOf(0) }
                    val tabs = listOf("Лицензия", "Регистрация", "Заметки")

                    Column(modifier = Modifier) {
                        TabRow(selectedTabIndex = tabIndex) {
                            tabs.forEachIndexed { index, title ->
                                Tab(
                                    selected = tabIndex == index,
                                    onClick = { tabIndex = index },
                                    text = { Text(title) }
                                )
                            }
                        }
                        when (tabIndex) {
                            0 -> LicenseScreen()
                            1 -> RegistrationScreen()
                            //2 -> NotesScreen()
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun LicenseScreen() {
    val context = LocalContext.current
    val licenseText by remember { mutableStateOf("Загрузка лицензии...") }
    val isAccepted by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        licenseText = loadTextFromAssets(context, "eula.txt")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedCard(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            Text(
                text = licenseText,
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = isAccepted,
                    onCheckedChange = { isAccepted = it }
                )
                Text("Я прочитал лицензионное соглашение")
            }
            Button(
                onClick = {
                    if (isAccepted) {
                        android.widget.Toast.makeText(
                            context,
                            "Лицензия принята!",
                            android.widget.Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        android.widget.Toast.makeText(
                            context,
                            "Прошу вас, подтвердите согласие",
                            android.widget.Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }) {
                Text("Принять")
            }
        }
    }
}

@Composable
fun RegistrationScreen() {
    val context = LocalContext.current
    val login by remember { mutableStateOf("") }
    val password by remember { mutableStateOf("") }
    val weakPasswordMessage by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    fun checkPassword(pwd: String) {
        if (pwd.isEmpty()) {
            weakPasswordMessage = ""
            return
        }

        coroutineScope.launch {
            val weakPasswords = loadLinesFromAssets(context, "weak_passwords.txt")
            if (weakPasswords.contains(pwd)) {
                weakPasswordMessage = "Этот пароль калл, переделывай"
            } else {
                weakPasswordMessage = ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedCard(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.outlinedCardColors()
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("РЕГИСТРАЦИЯ НА СВО", fontSize = 20.sp)

                OutlinedTextField(
                    value = login,
                    onValueChange = { login = it },
                    label = { Text("Логин") },
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        checkPassword(it)
                    },
                    label = { Text("Пароль") },
                    modifier = Modifier.fillMaxWidth()
                )

                if (weakPasswordMessage.isNotEmpty()) {
                    Text(
                        text = weakPasswordMessage,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp
                    )
                }

                Button(
                    onClick = {
                        if (login.isNotBlank() && password.isNotBlank()) {
                            android.widget.Toast.makeText(
                                context,
                                "Регистрация выполнена",
                                android.widget.Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            android.widget.Toast.makeText(
                                context,
                                "Заполните все поля!",
                                android.widget.Toast.LENGTH_SHORT
                            ).show()
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text("Зарегистрироваться")
                }
            }
        }
    }
}

suspend fun loadTextFromAssets(context: Context, fileName: String): String {
    return withContext(Dispatchers.IO) {
        try {
            val inputStream = context.assets.open(fileName)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val text = reader.readText()
            reader.close()
            text
        } catch (e: Exception) {
            "Ошибка загрузк файла: ${e.message}"
        }
    }
}

suspend fun loadLinesFromAssets(context: Context, fileName: String, data: String): Boolean {
    return withContext(Dispatchers.IO) {
        try {
            context.openFileOutput(fileName, Context.MODE_PRIVATE).use { outputStream ->
                outputStream.write(data.toByteArray())
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}