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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedData::class.java)

        text_title = binding.photoName
        text_date = binding.dateTaken
        et_description = binding.etDescription
        et_story = binding.etStory

        et_description.isEnabled = false
        et_story.isEnabled = false
        
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
    override fun onCreateOptionsMenu(menu: Menu, inflater: android.view.MenuInflater) {
        inflater.inflate(R.menu.menu_about, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list_back -> {
                val activity = requireActivity() as MainActivity
                activity.changePage(MainFragment())

            }

        }
        return super.onOptionsItemSelected(item)
    }
}