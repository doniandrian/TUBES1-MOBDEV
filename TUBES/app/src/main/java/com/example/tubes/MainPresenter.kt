package com.example.tubes

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
}