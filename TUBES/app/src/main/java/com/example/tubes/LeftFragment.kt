package com.example.tubes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.tubes.databinding.FragmentLeftBinding

class LeftFragment : Fragment() {
    private lateinit var binding: FragmentLeftBinding
    private lateinit var textHome: TextView
    private lateinit var textSettings: TextView
    private lateinit var textExit: TextView
    private lateinit var iconHome: ImageView
    private lateinit var iconSettings: ImageView
    private lateinit var iconExit: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLeftBinding.inflate(inflater, container, false)

        textHome = binding.textHome
        textHome.setOnClickListener {
            val activity = activity as MainActivity
            activity.changePage(MainFragment())
            activity.drawer.closeDrawers()
        }

        iconHome = binding.homeIcon
        iconHome.setOnClickListener {
            val activity = activity as MainActivity
            activity.changePage(MainFragment())
            activity.drawer.closeDrawers()
        }

        textSettings = binding.textSettings
        textSettings.setOnClickListener {
            val activity = activity as MainActivity
            activity.changePage(SettingFragment())
            activity.drawer.closeDrawers()
        }

        iconSettings = binding.settingsIcon
        iconSettings.setOnClickListener {
            val activity = activity as MainActivity
            activity.changePage(SettingFragment())
            activity.drawer.closeDrawers()
        }

        textExit = binding.textExit
        textExit.setOnClickListener {
            val activity = activity as MainActivity
            activity.closeApplicaton()
        }

        iconExit = binding.exitIcon
        iconExit.setOnClickListener {
            val activity = activity as MainActivity
            activity.closeApplicaton()
        }
        return binding.root
    }
}