package com.example.tubes.presenter

import com.example.tubes.model.DetailItem
import com.example.tubes.model.PhotoItem
import com.example.tubes.view.IMainFragment
import java.text.SimpleDateFormat
import java.util.*

class MainPresenter(private var photoList: MutableList<PhotoItem>, private var detailList: MutableList<DetailItem>, private var ui: IMainFragment) {

    fun addPhoto(imageUri: String, title: String, tanggal: String){
        val photoItem = PhotoItem(imageUri, title, tanggal)
        photoList.add(photoItem)
        ui.updateList(photoList)
    }

    fun addDetail(desc: String, story: String){
        val detailItem = DetailItem(desc, story)
        detailList.add(detailItem)
    }

    fun getCurrentDate(): String {
        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm aaa", Locale.getDefault())
        return dateFormat.format(currentDate)
    }

    fun updateDetail(desc: String, story: String, position: Int){
        detailList[position].desc = desc
        detailList[position].story = story
    }
}