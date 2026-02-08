package com.example.prac_four

import android.R.attr.onClick
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.prac_four.feature.SwipeToDeleteTask
import com.example.prac_four.types.BookSection


@Composable
fun SectionList(
    qurry: String,
    naveControl: NavHostController,
    filtered: List<BookSection>,
    sections: List<BookSection>,
    onDelete: (String) -> Unit,
    onAbout: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        items(filtered, key = { it.id }) { section ->
            Box {
                androidx.compose.animation.AnimatedVisibility(
                    visible = sections.any { it.id == section.id },
                    exit = shrinkVertically() + fadeOut()
                ) {
                    SwipeToDeleteTask(
                        qurry = qurry,
                        naveControl = naveControl,
                        section = section,
                        onDelete = {
                            onDelete(section.id)
                        }
                    )
                }
            }
        }

        item {
            Button(
                onClick = onAbout,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("AboutUs", color = Color.White)
            }
        }
    }
}
