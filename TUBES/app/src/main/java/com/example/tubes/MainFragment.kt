package com.example.tubes

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.ListView
import androidx.lifecycle.ViewModelProvider
import com.example.tubes.databinding.FragmentMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : Fragment(), IMainFragment {
    private lateinit var binding : FragmentMainBinding
    private lateinit var photoList: MutableList<PhotoItem>
    private lateinit var adapter: PhotoListAdapter
    private lateinit var listView: ListView
    private lateinit var btn_cam: FloatingActionButton
    private lateinit var imageUri: Uri
    private lateinit var penyimpananFoto: PenyimpananFoto
    lateinit var presenter: MainPresenter
    private lateinit var sharedViewModel: SharedData
    private lateinit var detailList: MutableList<DetailItem>
    private lateinit var penyimpananDetail: PenyimpananDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)

        val activity = activity as MainActivity

        activity.toolbar.title = "Photo Diary"

        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedData::class.java)

        penyimpananFoto = PenyimpananFoto(requireContext())
        penyimpananDetail = PenyimpananDetail(requireContext())

        listView = binding.lsPhoto
        btn_cam = binding.btnCam

        photoList = penyimpananFoto.loadPhotoList().toMutableList()
        detailList = penyimpananDetail.loadDetailList().toMutableList()

        adapter = PhotoListAdapter(activity, photoList,activity.statusdate)
        listView.adapter = adapter
        presenter = MainPresenter(photoList, detailList,this)

        //ambil data dari sharedViewModel
        if (sharedViewModel.imageUri != null) {
            val desc = sharedViewModel.desc
            val story = sharedViewModel.story
            val position = sharedViewModel.position
            if (desc != null) {
                if (story != null) {
                    presenter.updateDetail(desc, story, position!!)
                    //save to penyimpananDetail
                    penyimpananDetail.saveDetailList(detailList)


                }
            }
        }

        listView.setOnItemClickListener{ _, _, position, _ ->
            val photo = photoList[position]
            val detail = detailList[position]

            sharedViewModel.imageUri = photo.imageUri
            sharedViewModel.date = photo.tanggal
            sharedViewModel.desc = detail.desc
            sharedViewModel.story = detail.story
            sharedViewModel.position = position

            activity.changePage(DetailFragment())
        }

        val intentLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val currentDate = presenter.getCurrentDate()

                activity.changePage(AddDescFragment())

                sharedViewModel.imageUri = imageUri.toString()
                sharedViewModel.date = currentDate
            }
        }

        btn_cam.setOnClickListener {
            val values = ContentValues()
            values.put(MediaStore.Images.Media.TITLE, "My Image")
            values.put(MediaStore.Images.Media.DESCRIPTION, "Image taken from my app")
            imageUri = activity.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!

            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            intentLauncher.launch(takePictureIntent)
        }

        return binding.root
    }



    override fun updateList(photoList: List<PhotoItem>) {
        adapter.notifyDataSetChanged()
    }



}