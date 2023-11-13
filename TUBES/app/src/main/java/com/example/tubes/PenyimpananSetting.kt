package com.example.tubes

import android.content.Context
import android.content.SharedPreferences

class PenyimpananSetting(context:Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    // Key for the dark mode setting
    private val DARK_MODE_KEY = "dark_mode"
    private val DisplayDateTimeKey = "display_date_time"
    private val FontSizeKey = "font_size"

    fun isDarkModeEnabled(): Boolean {
        return sharedPreferences.getBoolean(DARK_MODE_KEY, false)
    }

    fun setDarkModeEnabled(enabled: Boolean) {
        editor.putBoolean(DARK_MODE_KEY, enabled)
        editor.apply()
    }

    fun isDisplayDateTimeEnabled(): Boolean {
        return sharedPreferences.getBoolean(DisplayDateTimeKey, true)
    }

    fun setDisplayDateTimeEnabled(enabled: Boolean) {
        editor.putBoolean(DisplayDateTimeKey, enabled)
        editor.apply()
    }

   fun setFontSize(size: String) {
        editor.putString(FontSizeKey, size)
        editor.apply()
    }

    fun getFontSize(): String? {
        return sharedPreferences.getString(FontSizeKey, "medium")
    }

}
