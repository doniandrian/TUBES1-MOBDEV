package com.example.tubes.view

import android.os.Bundle
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
import com.example.tubes.model.DetailItem
import com.example.tubes.model.PenyimpananDetail
import com.example.tubes.model.PenyimpananFoto
import com.example.tubes.model.PhotoItem
import com.example.tubes.model.SharedData
import com.example.tubes.presenter.MainPresenter

class AddDescFragment : Fragment(), IMainFragment {
    private lateinit var binding: FragmentAddDescriptionBinding
    private lateinit var imageView: ImageView
    private lateinit var btn_save: Button
    private lateinit var et_title: EditText
    private lateinit var et_desc: EditText
    private lateinit var et_story: EditText
    private lateinit var sharedViewModel: SharedData
    private lateinit var penyimpananDetail: PenyimpananDetail
    private lateinit var detailList: MutableList<DetailItem>
    private lateinit var photoList: MutableList<PhotoItem>
    private lateinit var penyimpananFoto: PenyimpananFoto
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: PhotoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddDescriptionBinding.inflate(inflater, container,false)

        val activity = activity as MainActivity

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedData::class.java)

        penyimpananDetail = PenyimpananDetail(requireContext())
        penyimpananFoto = PenyimpananFoto(requireContext())

        imageView = binding.ivPhotoAddDesc
        btn_save = binding.btnSave
        et_title = binding.etTitle
        et_desc = binding.etDescription
        et_story = binding.etStory

        photoList = penyimpananFoto.loadPhotoList().toMutableList()
        detailList = penyimpananDetail.loadDetailList().toMutableList()

        adapter = PhotoListAdapter(activity, photoList,true)
        presenter = MainPresenter(photoList, detailList,this)

        val imageUri = sharedViewModel.imageUri
        val currentDate = sharedViewModel.date

        imageView.setImageURI(imageUri?.toUri())

        btn_save.setOnClickListener {
            val title = et_title.text.toString()
            val desc = et_desc.text.toString()
            val story = et_story.text.toString()

            presenter.addDetail(desc, story)
            penyimpananDetail.saveDetailList(detailList)

            presenter.addPhoto(imageUri.toString(), title, currentDate.toString())
            penyimpananFoto.savePhotoList(photoList)

            activity.changePage(MainFragment())
        }

        return binding.root
    }

    override fun updateList(photoList: List<PhotoItem>) {
        adapter.notifyDataSetChanged()
    }
}