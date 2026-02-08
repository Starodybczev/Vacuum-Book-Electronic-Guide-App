package com.example.prac_four.feature

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight

fun highlightAll(
    text: String,
    query: String
): AnnotatedString {

    if (query.isBlank()) return AnnotatedString(text)

    val lowerText = text.lowercase()
    val lowerQuery = query.lowercase()

    return buildAnnotatedString {
        append(text)

        var startIndex = 0
        while (true) {
            val index = lowerText.indexOf(lowerQuery, startIndex)
            if (index == -1) break

            addStyle(
                style = SpanStyle(
                    background = Color.Yellow,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                ),
                start = index,
                end = index + query.length
            )

            startIndex = index + query.length
        }
    }
}
