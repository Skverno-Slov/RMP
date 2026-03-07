package com.example.labwork16

import android.os.Bundle
import android.widget.ScrollView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.labwork16.ui.theme.LabWork16Theme
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabWork16Theme {
                Column() {
                Column(Modifier.background(colorResource(R.color.pink)).height(250.dp).fillMaxWidth())
                {
                    Text(stringResource(R.string.app_name))
                    TextField( "", {},
                        label = {Text(stringResource(R.string.search))},
                        modifier =  Modifier.background(colorResource(R.color.bluvota)).height(dimensionResource(R.dimen.searchHeight))
                    )
                    Button({}) {
                        Text(stringResource(R.string.menu),
                            modifier = Modifier.background(colorResource(R.color.purple)).height(
                                dimensionResource(R.dimen.manuHeight)
                            ).width(
                                dimensionResource(R.dimen.manuWight)))
                    }
                }
                val scrollState = rememberScrollState()
                Column(Modifier.fillMaxSize().verticalScroll(scrollState).background(colorResource(R.color.pink))) {
                    val c = Calendar.getInstance()
                    val hour = c.get(Calendar.HOUR_OF_DAY)
                    val minute = c.get(Calendar.MINUTE)

                    val time = "${pluralStringResource(R.plurals.hours, hour, hour)} ${pluralStringResource(R.plurals.minute, minute, minute)}"

                    Text("Текущее время: $time")
                    Text("Разработчики:")
                    WriteDevs()
                }}
            }
        }
    }
}

@Composable
fun WriteDevs(){
    val devs = stringArrayResource(id = R.array.devs)
    LazyColumn (modifier = Modifier.height(200.dp)){
        items(devs){ name ->
            Text(text = stringResource(R.string.licence, name, 2026))
        }
    }
}