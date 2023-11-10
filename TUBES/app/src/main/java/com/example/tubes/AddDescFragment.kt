package com.example.tubes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.example.tubes.databinding.FragmentAddDescriptionBinding

class AddDescFragment : Fragment() {
    private lateinit var binding: FragmentAddDescriptionBinding
    private lateinit var imageView: ImageView
    private lateinit var btn_save: Button
    private lateinit var et_title: EditText
    private lateinit var communicator: Communicator
    var imageUri: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddDescriptionBinding.inflate(inflater, container,false)

        val activity = activity as MainActivity
        communicator = activity as Communicator

        imageView = binding.ivPhotoAddDesc
        btn_save = binding.btnSave
        et_title = binding.etTitle

        imageUri = arguments?.getString("imageUri")
        imageView.setImageURI(imageUri?.toUri())


        btn_save.setOnClickListener {
            activity.changePage(MainFragment())
        }

        return binding.root
    }
}