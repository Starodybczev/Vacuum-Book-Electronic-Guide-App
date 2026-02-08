package com.example.prac_four.pages


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prac_four.data.saveLastSection
import com.example.prac_four.data.vacuumBook


@Composable
fun SectionScreen(sectionId: String, paddingValues: PaddingValues) {

    val section = vacuumBook.find { it.id == sectionId }
        ?: run {
            Text("not found book")
            return
        }

    val context = LocalContext.current

    var scale by rememberSaveable { mutableStateOf(1f) }


    val animatedScale by animateFloatAsState(
        targetValue = scale,
        label = "text-scale"
    )





    LaunchedEffect(sectionId) {
        saveLastSection(context, sectionId)
    }

    Box {
        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp
                )
                .pointerInput(Unit) {
                    detectTransformGestures { _, _, zoom, _ ->
                        scale = (scale * zoom).coerceIn(0.8f, 2.2f)
                    }
                }
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = section.title,
                fontSize = 24.sp * animatedScale,
                lineHeight = (16.sp * scale) * 1.4f
            )

            Text(
                text = "Author: ${section.author}",
                fontSize = 12.sp * animatedScale,
                color = Color.Gray,
                lineHeight = (16.sp * scale) * 1.4f
            )

            Text(
                text = section.text,
                fontSize = 16.sp * animatedScale,
                lineHeight = (16.sp * scale) * 1.4f
            )
        }

        if (scale > 1.3f) {
            Button(
                onClick = { scale = 1f },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 20.dp, end = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2E2E2E),
                    contentColor = Color.White
                ),
                elevation = null // Убираем тень, чтобы кнопка выглядела плоской, как исходный текст
            ) {
                Text(
                    text = "Reset size",
                    fontSize = 14.sp
                )
            }
        }
    }

}
