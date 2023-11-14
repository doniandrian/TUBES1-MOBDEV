package com.example.tubes.view

import android.content.Context

interface ISettingFragment {
    interface Ui{

    }
    interface Presenter{
        fun updateAppDarkMode(isDarkModeEnabled: Boolean)
        fun showDialog(context: Context, activity: MainActivity)
    }
}