package com.example.tubes.presenter

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.tubes.R
import com.example.tubes.view.ISettingFragment
import com.example.tubes.view.MainActivity

class SettingPresenter() : ISettingFragment.Presenter {

    override fun updateAppDarkMode(isDarkModeEnabled: Boolean) {
        if (isDarkModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        //https://youtu.be/ilj3_O_GG_4?si=uYV2b2b7xxkFU5dy

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
            changeFontSize("small", activity)
            activity.statusfontsize = "small"
            dialog.dismiss()
            (activity.findViewById<ViewGroup>(android.R.id.content)).removeView(overlay)
        }

        medium.setOnClickListener {

            activity.sum = activity.textSizeFactor
            activity.statusBeforeFontSize = activity.statusfontsize
            changeFontSize("medium", activity)
            activity.statusfontsize = "medium"
            dialog.dismiss()
            (activity.findViewById<ViewGroup>(android.R.id.content)).removeView(overlay)
        }

        large.setOnClickListener {
            activity.sum = activity.textSizeFactor
            activity.statusBeforeFontSize = activity.statusfontsize
            changeFontSize("large", activity)
            activity.statusfontsize = "large"
            dialog.dismiss()
            (activity.findViewById<ViewGroup>(android.R.id.content)).removeView(overlay)
        }

        dialog.setOnDismissListener {
            (activity.findViewById<ViewGroup>(android.R.id.content)).removeView(overlay)
        }

        (activity.findViewById<ViewGroup>(android.R.id.content)).addView(overlay)

        dialog.show()

        //referensi: https://developer.android.com/guide/fragments/dialogs
    }

    override fun changeFontSize(size: String, activity: MainActivity) {
        if (activity.statusBeforeFontSize=="large" && size=="medium"){
            activity.textSizeFactor = -10
            updateTextSizesRecursive(activity.findViewById<ViewGroup>(android.R.id.content), activity)
        }
        else if (activity.statusBeforeFontSize=="large"&& size=="small"){
            activity.textSizeFactor = -20
            updateTextSizesRecursive(activity.findViewById<ViewGroup>(android.R.id.content), activity)
        }
        else if (activity.statusBeforeFontSize=="medium"&& size=="large"){
            activity.textSizeFactor = 10
            updateTextSizesRecursive(activity.findViewById<ViewGroup>(android.R.id.content), activity)
        }
        else if(activity.statusBeforeFontSize=="medium"&& size=="small"){
            activity.textSizeFactor = -10
            updateTextSizesRecursive(activity.findViewById<ViewGroup>(android.R.id.content), activity)
        }
        else if(activity.statusBeforeFontSize=="small"&& size=="large"){
            activity.textSizeFactor = 20
            updateTextSizesRecursive(activity.findViewById<ViewGroup>(android.R.id.content), activity)
        }
        else if(activity.statusBeforeFontSize=="small"&& size=="medium"){
            activity.textSizeFactor = 10
            updateTextSizesRecursive(activity.findViewById<ViewGroup>(android.R.id.content), activity)
        }
    }

    override fun updateTextSizesRecursive(view: View, activity: MainActivity) {
        if (view is TextView) {
            val newSize = view.textSize + activity.textSizeFactor
            view.setTextSize(TypedValue.COMPLEX_UNIT_PX, newSize)
        } else if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                updateTextSizesRecursive(view.getChildAt(i), activity)
            }
        }
    }

    override fun changeDisplayTime(status: Boolean, activity: MainActivity) {
        activity.statusdate = status
    }
}