package com.example.tubes

class MainPresenter(private var photoList: MutableList<PhotoItem>, private var ui: IMainFragment) {

    fun addPhoto(imageUri: String, tanggal: String){
        val photoItem = PhotoItem(imageUri, tanggal)
        photoList.add(photoItem)
        ui.updateList(photoList)
    }
}