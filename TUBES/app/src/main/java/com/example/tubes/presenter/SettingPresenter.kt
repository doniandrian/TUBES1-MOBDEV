package com.example.tubes.presenter

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.tubes.R
import com.example.tubes.view.ISettingFragment
import com.example.tubes.view.MainActivity



class SettingPresenter : ISettingFragment.Presenter {
    override fun updateAppDarkMode(isDarkModeEnabled: Boolean) {
        if (isDarkModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

    }
    override fun showDialog(context: Context, activity: MainActivity) {
        val overlay = View(context)
        overlay.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        overlay.setBackgroundColor(Color.parseColor("#80000000"))

        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        dialog.setContentView(R.layout.list_item_font_size)

        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog.window?.setLayout(width, height)

        dialog.setCanceledOnTouchOutside(true)


        val closeButton = dialog.findViewById<ImageView>(R.id.close)
        closeButton.setOnClickListener {
            dialog.dismiss()
            (activity.findViewById<ViewGroup>(android.R.id.content)).removeView(overlay)
        }

        val small = dialog.findViewById<Button>(R.id.buttonSmall)
        val medium = dialog.findViewById<Button>(R.id.buttonMedium)
        val large = dialog.findViewById<Button>(R.id.buttonLarge)

        small.setOnClickListener {
            activity.sum = activity.textSizeFactor
            activity.statusBeforeFontSize = activity.statusfontsize

            activity.changeFontSize("small")
            activity.statusfontsize = "small"
            dialog.dismiss()
            (activity.findViewById<ViewGroup>(android.R.id.content)).removeView(overlay)
        }
        medium.setOnClickListener {

            activity.sum = activity.textSizeFactor
            activity.statusBeforeFontSize = activity.statusfontsize
            activity.changeFontSize("medium")
            activity.statusfontsize = "medium"
            dialog.dismiss()
            (activity.findViewById<ViewGroup>(android.R.id.content)).removeView(overlay)
        }
        large.setOnClickListener {
            activity.sum = activity.textSizeFactor
            activity.statusBeforeFontSize = activity.statusfontsize
            activity.changeFontSize("large")
            activity.statusfontsize = "large"
            dialog.dismiss()
            (activity.findViewById<ViewGroup>(android.R.id.content)).removeView(overlay)
        }

        dialog.setOnDismissListener {
            (activity.findViewById<ViewGroup>(android.R.id.content)).removeView(overlay)
        }

        (activity.findViewById<ViewGroup>(android.R.id.content)).addView(overlay)

        dialog.show()
    }
}