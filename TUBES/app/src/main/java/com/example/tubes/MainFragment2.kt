package com.example.tubes

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainFragment2 : Fragment() {
    private lateinit var binding :MainFragment2
    private lateinit var photoList: MutableList<PhotoItem>
    private lateinit var rv_photo: RecyclerView
    private lateinit var btn_cam: FloatingActionButton
    private lateinit var imageUri: Uri
    private lateinit var penyimpananFoto: PenyimpananFoto
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
        val view = inflater.inflate(R.layout.fragment_main2, container, false)
        val activity = activity as MainActivity
        photoList = mutableListOf()

        rv_photo = view.findViewById(R.id.rv_photo)
        rv_photo.setHasFixedSize(true)


        rv_photo.layoutManager = LinearLayoutManager(requireContext())
        val listPhotoAdapter = PhotoListAdapter2(photoList)
        rv_photo.adapter = listPhotoAdapter

        rv_photo.itemAnimator = null



        btn_cam = view.findViewById(R.id.btn_cam)



        val tanggal = "2023"
        val judul = ""

        val intentLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val photoItem = PhotoItem(imageUri.toString(), judul, tanggal)
                listPhotoAdapter.addPhoto(photoItem)
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

        return view
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rv_photo.layoutManager = LinearLayoutManager(requireContext())
            }
            R.id.action_grid -> {
                rv_photo.layoutManager = GridLayoutManager(requireContext(), 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
