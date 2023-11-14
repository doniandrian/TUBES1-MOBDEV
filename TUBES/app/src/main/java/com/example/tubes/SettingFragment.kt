package com.example.tubes

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.tubes.databinding.ListItemFontSizeBinding
import com.example.tubes.databinding.SettingsBinding

class SettingFragment : Fragment() {
    private lateinit var binding: SettingsBinding
    private lateinit var binding2: ListItemFontSizeBinding
    private lateinit var btn_font_size : ImageView
    private lateinit var btn_about : ImageView
    private lateinit var PenyimpananSetting : PenyimpananSetting
    private lateinit var switch_dark_mode : Switch
    private lateinit var switch_display_time : Switch
    private lateinit var btnPopUp : Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SettingsBinding.inflate(inflater, container, false)
        binding2 = ListItemFontSizeBinding.inflate(inflater, container, false)
        btn_font_size = binding.fontSize
        btn_about = binding.About
        switch_dark_mode = binding.switchDarkMode
        switch_display_time = binding.switchDisplayTime

        PenyimpananSetting = PenyimpananSetting(requireContext())

        val activity = requireActivity() as MainActivity
        activity.toolbar.title = "Settings"

        switch_dark_mode.isChecked = PenyimpananSetting.isDarkModeEnabled()
        switch_display_time.isChecked = PenyimpananSetting.isDisplayDateTimeEnabled()

        btn_font_size.setOnClickListener {

            showDialog()

        }

        btn_about.setOnClickListener {
            activity.changePage(AboutFragment())
        }
        switch_dark_mode.setOnCheckedChangeListener { compundButton, isChecked ->
            PenyimpananSetting.setDarkModeEnabled(isChecked)
            updateAppDarkMode(isChecked)

        }
        binding.switchDisplayTime?.setOnCheckedChangeListener { compoundButton, isChecked ->
            PenyimpananSetting.setDisplayDateTimeEnabled(isChecked)

            activity.changeDisplayTime(isChecked)
        }

        return binding.root
    }

    fun updateAppDarkMode(isDarkModeEnabled: Boolean) {
        if (isDarkModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

    }
    private fun showDialog() {
        val overlay = View(requireContext())
        overlay.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        overlay.setBackgroundColor(Color.parseColor("#80000000"))

        val dialog = Dialog(requireContext())
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
            (requireActivity().findViewById<ViewGroup>(android.R.id.content)).removeView(overlay)
        }

        val small = dialog.findViewById<Button>(R.id.buttonSmall)
        val medium = dialog.findViewById<Button>(R.id.buttonMedium)
        val large = dialog.findViewById<Button>(R.id.buttonLarge)

        small.setOnClickListener {
            val activity = requireActivity() as MainActivity

            activity.changeFontSize("small")
            activity.statusfontsize = "small"
            dialog.dismiss()
            (requireActivity().findViewById<ViewGroup>(android.R.id.content)).removeView(overlay)
        }
        medium.setOnClickListener {
            val activity = requireActivity() as MainActivity

            activity.changeFontSize("medium")
            activity.statusfontsize = "medium"
            dialog.dismiss()
            (requireActivity().findViewById<ViewGroup>(android.R.id.content)).removeView(overlay)
        }
        large.setOnClickListener {
            val activity = requireActivity() as MainActivity

            activity.changeFontSize("large")
            activity.statusfontsize = "large"
            dialog.dismiss()
            (requireActivity().findViewById<ViewGroup>(android.R.id.content)).removeView(overlay)
        }

        dialog.setOnDismissListener {
            (requireActivity().findViewById<ViewGroup>(android.R.id.content)).removeView(overlay)
        }

        (requireActivity().findViewById<ViewGroup>(android.R.id.content)).addView(overlay)

        dialog.show()
    }



}