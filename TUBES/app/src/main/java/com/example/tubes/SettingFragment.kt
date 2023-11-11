package com.example.tubes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.tubes.databinding.SettingsBinding

class SettingFragment : Fragment() {
    private lateinit var binding: SettingsBinding
    private lateinit var btn_font_size : ImageView
    private lateinit var btn_about : ImageView



    private lateinit var PenyimpananSetting : PenyimpananSetting


    private lateinit var switch_dark_mode : Switch
    private lateinit var switch_display_time : Switch

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = SettingsBinding.inflate(inflater, container, false)
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
            activity.supportFragmentManager.beginTransaction().add(R.id.fontpopup,ListFontSizeFragment()
            )
                .show(ListFontSizeFragment()).commit()


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

}