package com.muradakhundov.jetflix.util.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

object PreferencesData {
    
    private const val PREFS_NAME = "JetPreferences"

    private fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    }

    fun saveString(context: Context, key: String, value: String) {
        val editor = getSharedPreferences(context).edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(context: Context, key: String, defaultValue: String): String {
        return getSharedPreferences(context).getString(key, defaultValue) ?: defaultValue
    }

}
