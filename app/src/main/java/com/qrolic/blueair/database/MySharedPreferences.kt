package com.qrolic.blueair.database

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class MySharedPreferences @Inject constructor(@ApplicationContext context: Context) {

    private val preferences: SharedPreferences = context.getSharedPreferences(
        PREFERENCE_NAME,
        0
    )
    private val editor: SharedPreferences.Editor = preferences.edit()

    private enum class Key {
        IS_FIRST_TIME_INSTALL,
        DEFAULT_CITY,
        SORT,
    }

    private fun get(key: Key, default: String): String {
        return preferences.getString(key.name, default)!!
    }

    private fun set(key: Key, value: String) {
        editor
            .putString(key.name, value)
            .apply()
    }

    var isFirstTimeInstall: Boolean
        get() = preferences.getBoolean(Key.IS_FIRST_TIME_INSTALL.name, true)
        set(value) =  editor
            .putBoolean(Key.IS_FIRST_TIME_INSTALL.name, value)
            .apply()

    var defaultCity: String
        get() = get(Key.DEFAULT_CITY, "ahmedabad")
        set(value) = set(Key.DEFAULT_CITY, value)


    var theme: String
        get() = get(Key.SORT, "theme 1")
        set(value) = set(Key.SORT, value)

    companion object {
        private const val PREFERENCE_NAME = "crypto_watch"
    }
}