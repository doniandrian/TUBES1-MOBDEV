package com.example.tubes

import androidx.appcompat.app.AppCompatDelegate
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



}