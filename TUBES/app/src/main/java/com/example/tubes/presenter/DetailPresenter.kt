package com.example.tubes.presenter

import android.app.Activity
import android.view.Menu
import android.widget.EditText
import com.example.tubes.view.MainActivity
import com.example.tubes.view.MainFragment
import com.example.tubes.R
import com.example.tubes.model.SharedData
import com.example.tubes.view.IDetailFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailPresenter : IDetailFragment.Presenter {

    override fun onEditButtonClick(btn_edit:FloatingActionButton, etDescription: EditText, etStory: EditText, sharedViewModel: SharedData) {
        if (etDescription.isEnabled && etStory.isEnabled) {
            etDescription.isEnabled = false
            etStory.isEnabled = false
            btn_edit.setImageResource(android.R.drawable.ic_menu_edit)
            etDescription.setText(sharedViewModel.desc)
            etStory.setText(sharedViewModel.story)

        } else {
            btn_edit.setImageResource(android.R.drawable.ic_menu_close_clear_cancel)
            etDescription.isEnabled = true
            etStory.isEnabled = true
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu, etDescription: EditText, etStory: EditText) {
        val saveMenuItem = menu.findItem(R.id.save)
        saveMenuItem.isVisible = etDescription.isEnabled && etStory.isEnabled
    }

    override fun onBackButtonClick(activity: Activity) {
        val mainActivity = activity as MainActivity
        mainActivity.changePage(MainFragment())
    }

    override fun onSaveButtonClick(desc: String, story: String, sharedViewModel: SharedData, activity: Activity) {
        sharedViewModel.desc = desc
        sharedViewModel.story = story

        val mainActivity = activity as MainActivity
        mainActivity.changePage(MainFragment())
    }
}
