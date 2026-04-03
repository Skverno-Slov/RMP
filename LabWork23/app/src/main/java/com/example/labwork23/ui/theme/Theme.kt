package com.example.labwork23.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF0061A4),
    onPrimary = Color(0xFFFFFFFF),
    secondary = Color(0xFF535F70),
    onSecondary = Color(0xFFFFFFFF),
    background = Color(0xFFFDFCFF)
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF9ECAFF),
    onPrimary = Color(0xFF003258),
    secondary = Color(0xFFBBC7DB),
    onSecondary = Color(0xFF253140),
    background = Color(0xFF1A1C1E)
)

private val LightColors2 = lightColorScheme(
    primary = Color(0xFF386A20),
    onPrimary = Color(0xFFFFFFFF),
    secondary = Color(0xFF55624C),
    onSecondary = Color(0xFFFFFFFF),
    background = Color(0xFFF9FFE9)
)

private val DarkColors2 = darkColorScheme(
    primary = Color(0xFF9CD67D),
    onPrimary = Color(0xFF0C3900),
    secondary = Color(0xFFBCCBB0),
    onSecondary = Color(0xFF273420),
    background = Color(0xFF10140E)
)

@Composable
fun LabWork23Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    alternativeStyle: Boolean,
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        alternativeStyle && darkTheme -> DarkColors2
        alternativeStyle && !darkTheme -> LightColors2
        !alternativeStyle && darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}