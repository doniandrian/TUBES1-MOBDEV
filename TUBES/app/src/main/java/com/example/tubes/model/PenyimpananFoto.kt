package com.example.tubes.model

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PenyimpananFoto(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("PhotoPrefs", Context.MODE_PRIVATE)

     fun savePhotoList(photoList: List<PhotoItem>) {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(photoList)
        editor.putString("photoList", json)
        editor.apply()
    }

    fun loadPhotoList(): List<PhotoItem> {
        val gson = Gson()
        val json = sharedPreferences.getString("photoList", null)
        val type = object : TypeToken<List<PhotoItem>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }
}

//referensi:
//https://www.geeksforgeeks.org/how-to-save-arraylist-to-sharedpreferences-in-android/