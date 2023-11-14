package com.example.tubes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
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
    private lateinit var text_title: TextView
    private lateinit var text_date: TextView
    private lateinit var et_description: EditText
    private lateinit var et_story: EditText
    private lateinit var sharedViewModel: SharedData
    private lateinit var btn_edit: FloatingActionButton
    private lateinit var secondPresenter: SecondPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: android.view.MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)

        secondPresenter = SecondPresenter()

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedData::class.java)

        text_title = binding.photoName
        text_date = binding.dateTaken
        et_description = binding.etDescription
        et_story = binding.etStory
        btn_edit = binding.btnEdit

        et_description.isEnabled = false
        et_story.isEnabled = false

        btn_edit.setImageResource(android.R.drawable.ic_menu_edit)

        btn_edit.setOnClickListener {
            secondPresenter.onEditButtonClick(et_description, et_story, sharedViewModel)
            requireActivity().invalidateOptionsMenu()
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

    override fun onPrepareOptionsMenu(menu: Menu) {
        secondPresenter.onPrepareOptionsMenu(menu, et_description, et_story)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.back -> {
                secondPresenter.onBackButtonClick(requireActivity())
            }
            R.id.save -> {
                secondPresenter.onSaveButtonClick(
                    et_description.text.toString(),
                    et_story.text.toString(),
                    sharedViewModel,
                    requireActivity()
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }
}