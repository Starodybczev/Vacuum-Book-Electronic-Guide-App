package com.example.prac_four

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import com.example.prac_four.data.loadDarkTheme
import com.example.prac_four.data.saveDarkTheme
import com.example.prac_four.ui.theme.Prac_fourTheme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val context = LocalContext.current
            var darkTheme by remember { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                darkTheme = loadDarkTheme(context)
            }

            Prac_fourTheme(darkTheme = darkTheme) {
                AppRouter(
                    onToggleTheme  = {
                        darkTheme = !darkTheme
                        lifecycleScope.launch {
                            saveDarkTheme(context, darkTheme)
                        }
                    }
                )
            }
        }
    }
}
