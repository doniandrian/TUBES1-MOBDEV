package com.example.tubes.view

import android.app.Activity
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.tubes.databinding.TampilanPhotoBinding
import com.example.tubes.model.PhotoItem

class PhotoListAdapter(private val activity: Activity, private val photoList: MutableList<PhotoItem>, private val status: Boolean) : BaseAdapter() {

    override fun getCount(): Int {
        return photoList.size
    }

    override fun getItem(i: Int): Any {
        return photoList[i]
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View {
        val binding = TampilanPhotoBinding.inflate(activity.layoutInflater)
        val viewHolder = ViewHolder(binding)

        val photoItem = getItem(i) as PhotoItem
        val imageUri = Uri.parse(photoItem.imageUri)

        viewHolder.updateView(photoItem, imageUri, status)

        return binding.root
    }

    private inner class ViewHolder(private val binding: TampilanPhotoBinding) {
        private val ivPhoto = binding.ivPhoto
        private val fotoJudul = binding.fotoJudul
        private val tanggalFoto = binding.tanggalFoto

        fun updateView(photoItem: PhotoItem, imageUri: Uri, status: Boolean) {
            ivPhoto.setImageURI(imageUri)
            fotoJudul.text = photoItem.title
            tanggalFoto.text = photoItem.tanggal

            tanggalFoto.visibility = if (status) View.VISIBLE else View.GONE
        }
    }
}