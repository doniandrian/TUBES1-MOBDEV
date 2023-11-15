package com.example.tubes.view

import android.content.Context
import android.view.View

interface ISettingFragment {
    interface Ui{
        fun updateAppTheme(isDarkModeEnabled: Boolean)
        fun updateAppDisplayDateTime(isDisplayDateTimeEnabled: Boolean)

    }
    interface Presenter{
        fun updateAppDarkMode(isDarkModeEnabled: Boolean)
        fun showDialog(context: Context, activity: MainActivity)
        fun changeFontSize(size: String, activity: MainActivity)
        fun updateTextSizesRecursive(view: View, activity: MainActivity)

        fun changeDisplayTime(status: Boolean, activity: MainActivity)
    }
}