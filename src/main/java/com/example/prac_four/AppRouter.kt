package com.example.prac_four

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.prac_four.components.Footer
import com.example.prac_four.components.Header
import com.example.prac_four.data.loadLastSection
import com.example.prac_four.data.saveLastSection
import com.example.prac_four.data.vacuumBook
import com.example.prac_four.pages.AboutAs
import com.example.prac_four.pages.SectionScreen

@Composable
fun AppRouter(onToggleTheme: () -> Unit) {
    val naveControl = rememberNavController()





    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val lastSection = loadLastSection(context)
        if (lastSection != null) {
            naveControl.navigate("section/$lastSection") {
                popUpTo("main")
            }
        }
    }

    Scaffold(
        topBar = { Header(
            naveControl, onToggleTheme = onToggleTheme,
            onAbout = { naveControl.navigate("about_us") }
        ) },
        bottomBar = { Footer() }
    ) { paddingValues ->
        NavHost(
            naveControl,
            startDestination = "main",
            modifier = Modifier.padding(paddingValues),
        ) {
            composable("main") {
                App(naveControl,
                    vacuumBook
                )
            }

            composable ("section/{id}", arguments = listOf(navArgument("id") { type = NavType.StringType })){
                backStackEntry ->
                    val id = backStackEntry.arguments?.getString("id")!!
                val navController = null
                SectionScreen(
                        sectionId = id,
                        paddingValues = paddingValues
                )
            }

            composable ("about_us"){
                AboutAs()
            }

        }
    }
}

