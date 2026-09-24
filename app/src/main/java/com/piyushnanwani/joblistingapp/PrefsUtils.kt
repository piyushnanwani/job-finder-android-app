package com.piyushnanwani.joblistingapp

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object PrefsUtils {
    private const val PREF_NAME = "user_pref"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun save(context: Context, key: String, value: String) {
        getPrefs(context).edit { putString(key, value) }
    }

    fun getString(context: Context, key: String): String? {
        return getPrefs(context).getString(key, null)
    }

    fun clear(context: Context) {
        getPrefs(context).edit { clear() }
    }


}