package com.example.showtracker.fonts

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.showtracker.R

object Typography {
    val robotoFont = FontFamily(
        Font(R.font.roboto_regular, FontWeight.Normal),
        Font(R.font.roboto_light, FontWeight.Light),
        Font(R.font.roboto_medium, FontWeight.Medium),
        Font(R.font.roboto_bold, FontWeight.Bold),
        Font(R.font.roboto_black, FontWeight.Black)
    )

    val nexaFont = FontFamily(
        Font(R.font.nexa_extralight, FontWeight.ExtraLight),
        Font(R.font.nexa_heavy, FontWeight.Normal)
    )

    val openSans = FontFamily(
        Font(R.font.open_sans_medium, FontWeight.Medium),
        Font(R.font.open_sans_semibold, FontWeight.SemiBold),
        Font(R.font.open_sans_bold, FontWeight.Bold)
    )
}