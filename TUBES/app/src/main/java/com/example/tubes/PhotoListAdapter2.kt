package com.example.tubes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView


class PhotoListAdapter2(private val listPhoto: MutableList<PhotoItem>) : RecyclerView.Adapter<PhotoListAdapter2.ListViewHolder>() {

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val photo: ImageView = view.findViewById(R.id.iv_photo)


    }
    fun addPhoto(photo: PhotoItem) {
        listPhoto.add(photo)
        notifyItemInserted(listPhoto.size - 1)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoListAdapter2.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.tampilan_photo, parent, false)
        return ListViewHolder(view)
    }


    override fun onBindViewHolder(holder: PhotoListAdapter2.ListViewHolder, position: Int) {
        val photoItem = listPhoto[position]
        val imageUri = android.net.Uri.parse(photoItem.imageUri)
        holder.photo.setImageURI(imageUri)

    }

    override fun getItemCount(): Int {
        return listPhoto.size
    }

}
