package com.example.tubes.view

import android.content.Context

interface ISettingFragment {
    interface Ui{
        fun updateAppTheme(isDarkModeEnabled: Boolean)
        fun updateAppDisplayDateTime(isDisplayDateTimeEnabled: Boolean)

    }
    interface Presenter{
        fun updateAppDarkMode(isDarkModeEnabled: Boolean)
        fun showDialog(context: Context, activity: MainActivity)
    }
}