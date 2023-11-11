package com.example.tubes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tubes.databinding.FragmentDetailBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var btn_edit: FloatingActionButton
    private lateinit var text_title: TextView
    private lateinit var text_date: TextView
    private lateinit var et_description: EditText
    private lateinit var et_story: EditText
    private lateinit var sharedViewModel: SharedData

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedData::class.java)

        btn_edit = binding.btnEdit
        text_title = binding.photoName
        text_date = binding.dateTaken
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

        val imageUri = sharedViewModel.imageUri
        val title = sharedViewModel.title
        val date = sharedViewModel.date
        val desc = sharedViewModel.desc
        val story = sharedViewModel.story

        binding.ivPhotoDetail.setImageURI(imageUri?.toUri())
        text_title.text = title
        text_date.text = date
        et_description.setText(desc)
        et_story.setText(story)

        return binding.root
    }
}