package com.example.tubes.model

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PenyimpananDetail(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("DetailPrefs", Context.MODE_PRIVATE)

    fun saveDetailList(detailList: List<DetailItem>) {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(detailList)
        editor.putString("detailList", json)
        editor.apply()
    }

    fun loadDetailList(): List<DetailItem> {
        val gson = Gson()
        val json = sharedPreferences.getString("detailList", null)
        val type = object : TypeToken<List<DetailItem>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }
}