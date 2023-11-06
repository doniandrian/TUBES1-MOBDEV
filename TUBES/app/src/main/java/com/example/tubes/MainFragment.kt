package com.example.tubes

import android.content.ContentValues
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.content.Intent
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainFragment : Fragment() {
    private lateinit var photoList: MutableList<PhotoItem>
    private lateinit var adapter: PhotoListAdapter
    private lateinit var btn_cam: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_main, container, false)

        val activity = activity as MainActivity

        photoList = mutableListOf()
        val listView = view.findViewById<ListView>(R.id.ls_photo)
        btn_cam = view.findViewById(R.id.btn_cam)

        adapter = PhotoListAdapter(activity, photoList)
        listView.adapter = adapter

        var values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "My Image")

        values.put(MediaStore.Images.Media.TITLE, "Image taken from my app")
        var imageUri = activity.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)!!
        var photoItem = PhotoItem(imageUri.toString())
        val intentLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                photoList.add(photoItem)
                adapter.notifyDataSetChanged()
            }
        }

        btn_cam.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            intentLauncher.launch(takePictureIntent)
        }

        return view
    }
}
