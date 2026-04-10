package com.example.labwork23.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.labwork23.R

val Baveuse = FontFamily(
    Font(R.font.baveuse, FontWeight.Normal),
    Font(R.font.baveuse, FontWeight.Bold) 
)

// Set of Material typography styles to start with
val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = Baveuse,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Baveuse,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Baveuse,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Baveuse,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp
    )
)