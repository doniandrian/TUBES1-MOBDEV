package com.example.tubes.view

import com.example.tubes.model.DetailItem
import com.example.tubes.model.PhotoItem

interface IAddDescFragment {
    interface Ui {
        fun updateList(photoList: List<PhotoItem>)
    }

    interface Presenter {
        fun addPhoto(photoList: MutableList<PhotoItem>, imageUri: String, title: String, tanggal: String)
        fun addDetail(detailList: MutableList<DetailItem>, desc: String, story: String)
    }

}