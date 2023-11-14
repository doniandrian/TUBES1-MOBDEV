package com.example.tubes

import android.app.Activity
import android.view.Menu
import android.widget.EditText

class SecondPresenter {

    fun onEditButtonClick(etDescription: EditText, etStory: EditText, sharedViewModel: SharedData) {
        if (etDescription.isEnabled && etStory.isEnabled) {
            etDescription.isEnabled = false
            etStory.isEnabled = false
        } else {
            etDescription.isEnabled = true
            etStory.isEnabled = true
        }
    }

    fun onPrepareOptionsMenu(menu: Menu, etDescription: EditText, etStory: EditText) {
        val saveMenuItem = menu.findItem(R.id.save)
        saveMenuItem.isVisible = etDescription.isEnabled && etStory.isEnabled
    }

    fun onBackButtonClick(activity: Activity) {
        val mainActivity = activity as MainActivity
        mainActivity.changePage(MainFragment())
    }

    fun onSaveButtonClick(desc: String, story: String, sharedViewModel: SharedData, activity: Activity) {
        sharedViewModel.desc = desc
        sharedViewModel.story = story

        val mainActivity = activity as MainActivity
        mainActivity.changePage(MainFragment())
    }
}
