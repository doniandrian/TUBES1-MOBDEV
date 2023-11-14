package com.example.tubes.view

import com.example.tubes.model.PhotoItem

interface IMainFragment {
    fun updateList(photoList: List<PhotoItem>)
}