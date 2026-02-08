package com.example.prac_four.feature

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.prac_four.components.SectionCard
import com.example.prac_four.data.vacuumBook
import com.example.prac_four.types.BookSection

@Composable
fun SwipeToDeleteTask(
    section: BookSection,
    onDelete: () -> Unit,
    naveControl: NavHostController,
    qurry: String,

    ){
    val cardShape = RoundedCornerShape(24.dp)

    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            if (value == SwipeToDismissBoxValue.EndToStart) {
                onDelete()
                true
            } else {
                false
            }
        }
    )


    Card(
        shape = cardShape,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ){
        SwipeToDismissBox(
            enableDismissFromStartToEnd = false,
            enableDismissFromEndToStart = true,
            state = dismissState,
            backgroundContent = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red)
                        .padding(end = 20.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            },
            content = {
                SectionCard(
                    query = qurry,
                    task = vacuumBook,
                    section = section,
                    onClick = {
                        naveControl.navigate("section/${section.id}")
                    }
                )
            }
        )
    }

}



