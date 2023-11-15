package com.example.tubes.view

import android.app.Activity
import android.view.Menu
import android.widget.EditText
import com.example.tubes.model.SharedData
import com.google.android.material.floatingactionbutton.FloatingActionButton
interface IDetailFragment{
    interface Ui {
        fun updateEditMode(enabled: Boolean)
        fun updateUI(imageUri: String, title: String, date: String, desc: String, story: String)


    }
    interface Presenter {
        fun onEditButtonClick(btn_edit:FloatingActionButton, etDescription: EditText, etStory: EditText, sharedViewModel: SharedData)
        fun onPrepareOptionsMenu(menu: Menu, etDescription: EditText, etStory: EditText)
        fun onBackButtonClick(activity: Activity)
        fun onSaveButtonClick(desc: String, story: String, sharedViewModel: SharedData, activity: Activity)
    }

}
