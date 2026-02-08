package com.example.prac_four

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.prac_four.types.BookSection

@Composable
fun Content(
    queue: String,
    naveControl: NavHostController,
    filtered: List<BookSection>,
    sections: List<BookSection>,
    onDelete: (String) -> Unit,
    onOpenSection: (String) -> Unit,
    onAbout: () -> Unit
) {
    if (filtered.isEmpty()) {
        EmptyState()
    } else {
        SectionList(
            qurry = queue,
            naveControl = naveControl,
            filtered = filtered,
            sections = sections,
            onDelete = onDelete,
            onAbout = onAbout
        )
    }
}
