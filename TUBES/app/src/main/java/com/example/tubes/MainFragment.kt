package com.example.tubes

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.ListView
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
    private lateinit var communicator: Communicator
    var judul: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)

        val activity = activity as MainActivity
        communicator = activity as Communicator

        penyimpananFoto = PenyimpananFoto(requireContext())
        photoList = penyimpananFoto.loadPhotoList().toMutableList()
        presenter = MainPresenter(photoList, this)

        listView = binding.lsPhoto
        btn_cam = binding.btnCam

        adapter = PhotoListAdapter(activity, photoList)
        listView.adapter = adapter

        listView.setOnItemClickListener{ _, _, position, _ ->
            val photo = photoList[position]
            communicator.passImageUri2(photo.imageUri)
        }

        val intentLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val currentDate = getCurrentDate()
                presenter.addPhoto(imageUri.toString(), currentDate)
                penyimpananFoto.savePhotoList(photoList)

                activity.changePage(AddDescFragment())

                communicator.passImageUri(imageUri.toString())
                communicator.passDate(currentDate)
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