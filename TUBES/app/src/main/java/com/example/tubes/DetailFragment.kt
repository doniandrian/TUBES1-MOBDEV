package com.example.tubes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailFragment : Fragment() {
    private lateinit var btn_edit: FloatingActionButton
    private lateinit var et_description: EditText
    private lateinit var et_story: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        btn_edit = view.findViewById(R.id.btn_edit)
        et_description = view.findViewById(R.id.et_description)
        et_story = view.findViewById(R.id.et_story)

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

        return view
    }
}