package com.org.tmdb.ui.common

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.org.tmdb.ui.theme.primaryDarkMode
import com.org.tmdb.ui.theme.rainbowColorsForLoader
import com.org.tmdb.ui.theme.secondaryDarkMode

@Composable
fun CommonSpacer(
    int: Int,
    modifier: Modifier = Modifier
) {
    Spacer(
        modifier = modifier
            .fillMaxWidth()
            .height(int.dp)
    )
}


@Composable
fun TMDBProgressLoader(){
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val rotateAnimation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(1000, easing = LinearEasing)), label = ""
    )
    val colorList = rainbowColorsForLoader
    Canvas(modifier = Modifier
        .size(size = 70.dp)
        .padding(10.dp)
    ){
        rotate(rotateAnimation.value) {
            drawCircle(
                brush = Brush.sweepGradient(colorList)
            )
        }
    }
}



@Preview
@Composable
fun LoaderPreview(){
    TMDBProgressLoader()
}