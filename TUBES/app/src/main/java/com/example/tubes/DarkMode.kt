package com.example.tubes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.tubes.databinding.SettingsBinding


class DarkMode : AppCompatActivity() {
    private lateinit var binding: SettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.switchDarkMode?.setOnCheckedChangeListener { compundButton, isChecked ->
            when (isChecked) {
                true -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }

                false -> {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }

            }

        }
    }
}