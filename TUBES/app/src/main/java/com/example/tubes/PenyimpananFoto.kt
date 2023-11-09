package com.example.tubes

import android.content.Context

class PenyimpananFoto(activity: MainActivity) {
    private val sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)

    fun saveImageUri(uri: String) {
        with (sharedPreferences.edit()) {
            putString("image_uri", uri)
            apply()
        }
    }

    fun loadImageUri(): String? {
        return sharedPreferences.getString("image_uri", null)
    }
}