package com.example.prac_four

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.prac_four.types.BookSection

@Composable
fun AppContent(
    naveControl: NavHostController,
    focusManager: FocusManager,
    search: String,
    onSearchChange: (String) -> Unit,
    filtered: List<BookSection>,
    sections: List<BookSection>,
    onDelete: (String) -> Unit,
    onOpenSection: (String) -> Unit,
    onAbout: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                focusManager.clearFocus()
            }
            .fillMaxSize()
            .padding(
                start = 5.dp,
                end = 5.dp,
                top = 16.dp,
                bottom = 20.dp
            ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        AppHeader()

        SearchInput(
            value = search,
            onValueChange = onSearchChange
        )

        Content(
            queue = search,
            naveControl = naveControl,
            filtered = filtered,
            sections = sections,
            onDelete = onDelete,
            onOpenSection = onOpenSection,
            onAbout = onAbout
        )
    }
}

