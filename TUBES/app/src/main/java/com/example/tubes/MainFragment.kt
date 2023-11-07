package com.example.tubes

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.ListView

class MainFragment : Fragment() {
    private lateinit var photoList: MutableList<PhotoItem>
    private lateinit var adapter: PhotoListAdapter
    private lateinit var listView: ListView
    private lateinit var btn_cam: FloatingActionButton
    private lateinit var imageUri: Uri
    private lateinit var penyimpananFoto: PenyimpananFoto

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        val activity = activity as MainActivity

        photoList = mutableListOf()
        listView = view.findViewById(R.id.ls_photo)
        btn_cam = view.findViewById(R.id.btn_cam)

        adapter = PhotoListAdapter(activity, photoList)
        listView.adapter = adapter

        // Initialize the PhotoStorageManager
        penyimpananFoto = PenyimpananFoto(activity)
        val savedUri = penyimpananFoto.loadImageUri()
        if (savedUri != null) {
            val photoItem = PhotoItem(savedUri)
            photoList.add(photoItem)
            adapter.notifyDataSetChanged()
        }

        val intentLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val photoItem = PhotoItem(imageUri.toString())
                photoList.add(photoItem)
                adapter.notifyDataSetChanged()
                penyimpananFoto.saveImageUri(imageUri.toString())
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
}
