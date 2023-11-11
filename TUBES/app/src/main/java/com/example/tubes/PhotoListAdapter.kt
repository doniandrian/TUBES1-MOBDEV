package com.example.tubes

import android.app.Activity
import android.net.Uri
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class PhotoListAdapter(private val activity: Activity, private val photoList: MutableList<PhotoItem>, private val detailList: MutableList<DetailItem>) : BaseAdapter() {

    override fun getCount(): Int {
        return photoList.size
    }

    override fun getItem(i: Int): Any {
        return photoList[i]
    }

    fun getItemDetail(i: Int): Any {
        return detailList[i]
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View {
        val view: View = activity.layoutInflater.inflate(R.layout.tampilan_photo, null)
        val viewHolder = ViewHolder(view)

        val photoItem = getItem(i) as PhotoItem
        val detailItem = getItemDetail(i) as DetailItem
        val imageUri = Uri.parse(photoItem.imageUri)
        viewHolder.photo.setImageURI(imageUri)
        viewHolder.judul.text = detailItem.title
        viewHolder.tanggal.text = photoItem.tanggal

        return view
    }

    private class ViewHolder(view: View) {
        val photo: ImageView = view.findViewById(R.id.iv_photo)
        val judul: TextView = view.findViewById(R.id.foto_judul)
        val tanggal: TextView = view.findViewById(R.id.tanggal_foto)
    }
}