package com.example.tubes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.example.tubes.databinding.FragmentDetailBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailFragment : Fragment() {
    private lateinit var binding : FragmentDetailBinding
    private lateinit var btn_edit: FloatingActionButton
    private lateinit var et_description: EditText
    private lateinit var et_story: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)

        btn_edit = binding.btnEdit
        et_description = binding.etDescription
        et_story = binding.etStory

        et_description.isEnabled = false
        et_story.isEnabled = false

        btn_edit.setImageResource(android.R.drawable.ic_menu_edit)

        btn_edit.setOnClickListener {
            if (et_description.isEnabled && et_story.isEnabled) {
                btn_edit.setImageResource(android.R.drawable.ic_menu_edit)
                et_description.isEnabled = false
                et_story.isEnabled = false
            } else {
                btn_edit.setImageResource(android.R.drawable.ic_menu_save)
                et_description.isEnabled = true
                et_story.isEnabled = true
            }
        }

        val imageUriString = arguments?.getString("imageUri")

        binding.ivPhotoDetail.setImageURI(imageUriString?.toUri())

        return binding.root
    }
}
