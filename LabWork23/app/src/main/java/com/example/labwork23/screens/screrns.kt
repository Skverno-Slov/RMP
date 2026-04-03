package com.example.labwork23.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BankScreen() {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Card(modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )) {
            Column(Modifier.padding(16.dp)) {
                Text("Баланс", style = MaterialTheme.typography.labelSmall)
                Text("124 500,00 ₽", style = MaterialTheme.typography.headlineLarge)
            }
        }

        OutlinedTextField(value = "", onValueChange = {}, label = { Text("Сумма перевода") }, modifier = Modifier.fillMaxWidth())
        Row(Modifier.fillMaxWidth().padding(top = 16.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {}, modifier = Modifier, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)) { Text("Перевести", style = MaterialTheme.typography.bodyLarge) }
            Button(onClick = {}, modifier = Modifier, colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)) { Text("Пополнить", style = MaterialTheme.typography.bodyLarge) }
        }
    }
}

@Composable
fun TaskScreen() {
    var text by remember { mutableStateOf("") }
    Column(Modifier.fillMaxSize()) {
        OutlinedTextField(value = text, onValueChange = { text = it }, placeholder = { Text("Слетать на остров...",  style = MaterialTheme.typography.labelSmall) }, modifier = Modifier.fillMaxWidth())
        repeat(3) { i ->
            var checked by remember { mutableStateOf(false) }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = checked, colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colorScheme.secondary), onCheckedChange = { checked = it })
                Text("Задача №${i + 1}", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Composable
fun ShopScreen() {
    val items = listOf("Кроссовки", "Худи", "Рюкзак")
    Column(Modifier.fillMaxSize()) {
        LazyColumn() {
            items(items.size) { index ->
                ListItem(
                    headlineContent = { Text(items[index], style = MaterialTheme.typography.bodyLarge) },
                    supportingContent = { Text("Цена: ${(index + 1) * 1000} руб.", style = MaterialTheme.typography.labelSmall) },
                    trailingContent = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.ShoppingCart, "")
                        }
                    },
                    colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surface)
                )
            }
        }
    }
}