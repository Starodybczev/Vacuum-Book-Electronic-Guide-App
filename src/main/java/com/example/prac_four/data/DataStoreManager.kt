package com.example.prac_four.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore(name = "reader_prefs")

object ReaderPrefs {
    val LAST_SECTION = stringPreferencesKey("last_section")
    val DARK_THEME = booleanPreferencesKey("dark_theme")
}



suspend fun saveLastSection(context: Context, sectionId: String) {
    context.dataStore.edit { prefs ->
        prefs[ReaderPrefs.LAST_SECTION] = sectionId
    }
}

suspend fun loadLastSection(context: Context): String? {
    val prefs = context.dataStore.data.first()
    return prefs[ReaderPrefs.LAST_SECTION]
}


suspend fun saveDarkTheme(context: Context, value: Boolean) {
    context.dataStore.edit { prefs ->
        prefs[ReaderPrefs.DARK_THEME] = value
    }
}

suspend fun loadDarkTheme(context: Context): Boolean {
    val prefs = context.dataStore.data.first()
    return prefs[ReaderPrefs.DARK_THEME] ?: false
}