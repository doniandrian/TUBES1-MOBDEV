package com.example.tubes

import android.app.Activity
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView

class PhotoListAdapter(private val activity: Activity, private val portofolioList: MutableList<PhotoItem>) : BaseAdapter() {

    override fun getCount(): Int {
        return portofolioList.size
    }

    override fun getItem(i: Int): Any {
        return portofolioList[i]
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(i: Int, view: View?, viewGroup: ViewGroup?): View {
        val view: View = activity.layoutInflater.inflate(R.layout.item_list_photo, null)
        val viewHolder: ViewHolder = ViewHolder(view)

        val photoItem = getItem(i) as PhotoItem
        val imageUri = Uri.parse(photoItem.imageUri)
        viewHolder.photo.setImageURI(imageUri)

        return view
    }


    private class ViewHolder(view: View) {
        val photo: ImageView = view.findViewById(R.id.iv_photo)
    }
}