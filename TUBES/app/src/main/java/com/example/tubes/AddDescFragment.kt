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
import androidx.lifecycle.ViewModelProvider
import com.example.tubes.databinding.FragmentAddDescriptionBinding

class AddDescFragment : Fragment() {
    private lateinit var binding: FragmentAddDescriptionBinding
    private lateinit var imageView: ImageView
    private lateinit var btn_save: Button
    private lateinit var et_title: EditText
    private lateinit var et_desc: EditText
    private lateinit var et_story: EditText
    private lateinit var sharedViewModel: SharedData
    private lateinit var penyimpananDetail: PenyimpananDetail
    private lateinit var detailList: MutableList<DetailItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddDescriptionBinding.inflate(inflater, container,false)

        val activity = activity as MainActivity

        penyimpananDetail = PenyimpananDetail(requireContext())

        imageView = binding.ivPhotoAddDesc
        btn_save = binding.btnSave
        et_title = binding.etTitle
        et_desc = binding.etDescription
        et_story = binding.etStory

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedData::class.java)
        val imageUri = sharedViewModel.imageUri

        detailList = penyimpananDetail.loadDetailList().toMutableList()

        imageView.setImageURI(imageUri?.toUri())

        btn_save.setOnClickListener {
            val detailItem = DetailItem(et_title.text.toString(), et_desc.text.toString(), et_story.text.toString())
            detailList.add(detailItem)
            penyimpananDetail.saveDetailList(detailList)
            activity.changePage(MainFragment())
        }

        return binding.root
    }
}