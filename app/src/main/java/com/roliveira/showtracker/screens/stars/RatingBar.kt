package com.roliveira.showtracker.screens.stars

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.roliveira.showtracker.R
import kotlin.math.ceil
import kotlin.math.floor

@Composable
fun FractionalRatingBar(
    rating: Float,
    maxRating: Int = 5,
    filledColor: Color = colorResource(id = R.color.pink),
    emptyColor: Color = Color(0x20FFFFFF)
) {
    val density = LocalDensity.current.density
    val starSize = (6f * density).dp

    Row(
        horizontalArrangement = Arrangement.Start,
    ) {
        val fullStars = floor(rating).toInt()
        val partialStarFill = rating - fullStars
        val emptyStars = maxRating - ceil(rating).toInt()

        repeat(fullStars) {
            Icon(Icons.Filled.Star, contentDescription = null, tint = filledColor, modifier = Modifier.size(starSize))
        }

        if (partialStarFill > 0) {
            PartialStar(
                starSize = starSize - 3.dp,
                filledColor = filledColor,
                emptyColor = emptyColor,
                fillPercentage = partialStarFill
            )
        }

        repeat(emptyStars) {
            Icon(Icons.Filled.Star, contentDescription = null, tint = emptyColor, modifier = Modifier.size(starSize))
        }
    }
}

@Composable
private fun PartialStar(
    starSize: Dp,
    filledColor: Color,
    emptyColor: Color,
    fillPercentage: Float
) {
    Canvas(modifier = Modifier.size(starSize)) {
        val starPath = createStarPath(size.width, size.height)

        translate(0f, 1.dp.toPx()) {
            clipRect(
                left = 0f,
                top = 0f,
                right = size.width * fillPercentage,
                bottom = size.height
            ) {
                drawPath(starPath, color = filledColor)
            }
            clipRect(
                left = size.width * fillPercentage,
                top = 0f,
                right = size.width,
                bottom = size.height
            ) {
                drawPath(starPath, color = emptyColor)
            }
        }
    }
}


private fun createStarPath(width: Float, height: Float): Path {
    val path = Path()
    path.moveTo(0.5f * width, 0.0f * height)
    path.lineTo(0.628f * width, 0.377f * height)
    path.lineTo(1.0f * width, 0.377f * height)
    path.lineTo(0.688f * width, 0.613f * height)
    path.lineTo(0.809f * width, 1.0f * height)
    path.lineTo(0.5f * width, 0.753f * height)
    path.lineTo(0.191f * width, 1.0f * height)
    path.lineTo(0.312f * width, 0.613f * height)
    path.lineTo(0.0f * width, 0.377f * height)
    path.lineTo(0.372f * width, 0.377f * height)
    path.close()
    return path
}