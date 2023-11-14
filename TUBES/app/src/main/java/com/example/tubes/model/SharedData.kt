package com.example.tubes.model

import androidx.lifecycle.ViewModel
class SharedData : ViewModel() {
    var imageUri: String? = null
    var date: String? = null
    var title: String? = null
    var desc: String? = null
    var story: String? = null
    var position: Int? = null
}