package com.example.showtracker.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun ShowTrackerTheme(
    content: @Composable () -> Unit
) {

    val view = LocalView.current
    val window = (view.context as Activity).window

    val colorScheme = LightColorScheme
    window.statusBarColor = MaterialTheme.colorScheme.background.toArgb()

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}