package com.example.tubes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


import androidx.fragment.app.Fragment
import com.example.tubes.databinding.ListItemFontSizeBinding

class ListFontSizeFragment : Fragment() {
    private lateinit var binding: ListItemFontSizeBinding
    private lateinit var small : androidx.appcompat.widget.AppCompatButton
    private lateinit var medium : androidx.appcompat.widget.AppCompatButton
    private lateinit var large : androidx.appcompat.widget.AppCompatButton
    private lateinit var close : ImageView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListItemFontSizeBinding.inflate(inflater, container, false)

        val activity = requireActivity() as MainActivity

        small = binding.buttonSmall
        medium = binding.buttonMedium
        large = binding.buttonLarge
        close = binding.close

        small.setOnClickListener {
            activity.changeFontSize(1.2f)
            activity.supportFragmentManager.beginTransaction().remove(this).commit()
        }
        medium.setOnClickListener {
            activity.changeFontSize(1.0f)
            activity.supportFragmentManager.beginTransaction().remove(this).commit()
        }
        large.setOnClickListener {
            activity.changeFontSize(0.8f)
            activity.supportFragmentManager.beginTransaction().remove(this).commit()
        }
        close.setOnClickListener {
            activity.supportFragmentManager.beginTransaction().remove(this).commit()
        }

        return binding.root
    }
}