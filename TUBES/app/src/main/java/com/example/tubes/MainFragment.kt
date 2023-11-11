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
    private lateinit var presenter: MainPresenter
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

        adapter = PhotoListAdapter(activity, photoList)
        listView.adapter = adapter

        listView.setOnItemClickListener{ _, _, position, _ ->
            val photo = photoList[position]
            val detail = detailList[position]

            sharedViewModel.imageUri = photo.imageUri
            sharedViewModel.date = photo.tanggal
            sharedViewModel.desc = detail.desc
            sharedViewModel.story = detail.story

            activity.changePage(DetailFragment())
        }

        val intentLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val currentDate = getCurrentDate()

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
    override fun onCreateOptionsMenu(menu: Menu, inflater: android.view.MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun updateList(photoList: List<PhotoItem>) {
        adapter.notifyDataSetChanged()
    }

    private fun getCurrentDate(): String {
        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm aaa", Locale.getDefault())
        return dateFormat.format(currentDate)
    }

    override fun onDestroy() {
        super.onDestroy()
        penyimpananFoto.savePhotoList(photoList)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                // TODO()
            }
            R.id.action_grid -> {
                // TODO()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}