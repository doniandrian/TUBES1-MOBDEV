package com.example.tubes.presenter

import com.example.tubes.model.DetailItem
import com.example.tubes.model.PhotoItem
import com.example.tubes.view.IAddDescFragment

class AddDescPresenter: IAddDescFragment.Presenter {

    override fun addPhoto(photoList: MutableList<PhotoItem>, imageUri: String, title: String, tanggal: String){
        val photoItem = PhotoItem(imageUri, title, tanggal)
        photoList.add(photoItem)
    }

    override fun addDetail(detailList: MutableList<DetailItem>, desc: String, story: String){
        val detailItem = DetailItem(desc, story)
        detailList.add(detailItem)
    }
}