package com.example.prac_four.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prac_four.feature.highlightAll
import com.example.prac_four.types.BookSection

@Composable
fun SectionCard(
    query: String,
    section: BookSection? = null,
    onClick: (() -> Unit)? = null,
    task: List<BookSection>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick?.invoke() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = highlightAll(section?.title ?: "", query),
                fontSize = 18.sp
            )

            Text(
                text = section?.preview ?: "unknown",
                fontSize = 14.sp,
                color = Color.Gray,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 6.dp)
            )

            Text(
                text = section?.author ?: "unknown" ,
                fontSize = 12.sp,
                color = Color.DarkGray,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .align(Alignment.End)
            )
        }
    }
}

