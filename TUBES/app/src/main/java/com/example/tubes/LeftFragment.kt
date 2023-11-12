package com.example.tubes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.tubes.databinding.FragmentLeftBinding

class LeftFragment : Fragment() {
    private lateinit var binding: FragmentLeftBinding

    private lateinit var iconHome: LinearLayout
    private lateinit var iconSettings: LinearLayout
    private lateinit var iconExit: LinearLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLeftBinding.inflate(inflater, container, false)

        iconHome = binding.containerHome
        iconHome.setOnClickListener {
            val activity = activity as MainActivity
            activity.changePage(MainFragment())
            activity.drawer.closeDrawers()
        }



        iconSettings = binding.containerSettings
        iconSettings.setOnClickListener {
            val activity = activity as MainActivity
            activity.changePage(SettingFragment())
            activity.drawer.closeDrawers()
        }



        iconExit = binding.containerExit
        iconExit.setOnClickListener {
            val activity = activity as MainActivity
            activity.closeApplicaton()
        }
        return binding.root
    }
}