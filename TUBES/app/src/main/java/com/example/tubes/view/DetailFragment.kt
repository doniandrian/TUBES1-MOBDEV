package com.example.tubes.view

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
import com.example.tubes.R
import com.example.tubes.databinding.FragmentDetailBinding
import com.example.tubes.model.SharedData
import com.example.tubes.presenter.DetailPresenter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailFragment : Fragment() , IDetailFragment.Ui {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var text_title: TextView
    private lateinit var text_date: TextView
    private lateinit var et_description: EditText
    private lateinit var et_story: EditText
    private lateinit var sharedViewModel: SharedData
    private lateinit var btn_edit: FloatingActionButton
    private lateinit var detailPresenter: DetailPresenter

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

        detailPresenter = DetailPresenter()

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
            detailPresenter.onEditButtonClick(btn_edit, et_description, et_story, sharedViewModel)
            requireActivity().invalidateOptionsMenu()
        }

        val imageUri = sharedViewModel.imageUri
        val title = sharedViewModel.title
        val date = sharedViewModel.date
        val desc = sharedViewModel.desc
        val story = sharedViewModel.story


        updateUI(imageUri!!, title!!, date!!, desc!!, story!!)

        return binding.root
    }
    override fun updateEditMode(enabled: Boolean) {
        et_description.isEnabled = enabled
        et_story.isEnabled = enabled
        btn_edit.setImageResource(if (enabled) (android.R.drawable.ic_menu_close_clear_cancel) else android.R.drawable.ic_menu_edit)
        et_description.setText(sharedViewModel.desc)
        et_story.setText(sharedViewModel.story)
    }

    override fun updateUI(imageUri:String, title: String, date: String, desc: String, story: String) {
        binding.ivPhotoDetail.setImageURI(imageUri.toUri())
        text_title.text = title
        text_date.text = date
        et_description.setText(desc)
        et_story.setText(story)
    }


    override fun onPrepareOptionsMenu(menu: Menu) {
        detailPresenter.onPrepareOptionsMenu(menu, et_description, et_story)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.back -> {
                detailPresenter.onBackButtonClick(requireActivity())
            }
            R.id.save -> {
                detailPresenter.onSaveButtonClick(
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