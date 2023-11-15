package com.example.tubes.view

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
import com.example.tubes.R
import com.example.tubes.databinding.ListItemFontSizeBinding
import com.example.tubes.databinding.SettingsBinding
import com.example.tubes.model.PenyimpananSetting
import com.example.tubes.presenter.SettingPresenter

class SettingFragment : Fragment(), ISettingFragment.Ui {
    private lateinit var binding: SettingsBinding
    private lateinit var binding2: ListItemFontSizeBinding
    private lateinit var btn_font_size : ImageView
    private lateinit var btn_about : ImageView
    private lateinit var PenyimpananSetting : PenyimpananSetting
    private lateinit var switch_dark_mode : Switch
    private lateinit var switch_display_time : Switch
    private lateinit var SettingPresenter : SettingPresenter

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
        SettingPresenter = SettingPresenter()

        updateAppTheme(PenyimpananSetting.isDarkModeEnabled())
        updateAppDisplayDateTime(PenyimpananSetting.isDisplayDateTimeEnabled())

        btn_font_size.setOnClickListener {

            SettingPresenter.showDialog(requireContext(), activity)

        }

        btn_about.setOnClickListener {
            activity.changePage(AboutFragment())
        }
        switch_dark_mode.setOnCheckedChangeListener { compundButton, isChecked ->
            PenyimpananSetting.setDarkModeEnabled(isChecked)
            SettingPresenter.updateAppDarkMode(isChecked)

        }
        binding.switchDisplayTime?.setOnCheckedChangeListener { compoundButton, isChecked ->
            PenyimpananSetting.setDisplayDateTimeEnabled(isChecked)

            activity.changeDisplayTime(isChecked)
        }

        return binding.root
    }

    override fun updateAppTheme(isDarkModeEnabled: Boolean) {
        switch_dark_mode.isChecked = isDarkModeEnabled
    }

    override fun updateAppDisplayDateTime(isDisplayDateTimeEnabled: Boolean) {
        switch_display_time.isChecked = isDisplayDateTimeEnabled
    }


}