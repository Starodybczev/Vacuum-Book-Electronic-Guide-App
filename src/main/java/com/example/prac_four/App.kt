package com.example.prac_four


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavHostController
import com.example.prac_four.types.BookSection
import kotlinx.coroutines.delay


@Composable
fun App(
    naveControl: NavHostController,
    vacuumBook: List<BookSection>
) {
    val focusManager = LocalFocusManager.current

    var search by remember { mutableStateOf("") }
    var debouncedSearch by remember { mutableStateOf("") }
    var sections by remember { mutableStateOf(vacuumBook) }

    LaunchedEffect(search) {
        delay(300)
        debouncedSearch = search
    }

    val filtered = remember(sections, debouncedSearch) {
        sections.filter {
            it.title.contains(debouncedSearch, ignoreCase = true)
        }
    }

    AppContent(
        naveControl = naveControl,
        focusManager = focusManager,
        search = search,
        onSearchChange = { search = it },
        filtered = filtered,
        sections = sections,
        onDelete = { id ->
            sections = sections.filter { it.id != id }
        },
        onOpenSection = { id ->
            naveControl.navigate("section/$id")
        },
        onAbout = {
            naveControl.navigate("about_us")
        }
    )
}


