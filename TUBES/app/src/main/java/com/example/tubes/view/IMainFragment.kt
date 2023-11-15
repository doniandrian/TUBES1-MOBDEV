package com.example.tubes.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.tubes.model.DetailItem

interface IMainFragment {
    interface Presenter {
        fun getCurrentDate(): String
        fun updateDetail(detailList: MutableList<DetailItem>, desc: String, story: String, position: Int)
        fun darkModeEnable(activity: MainActivity)
        fun closeApplicaton(activity: MainActivity)
    }
    interface Ui{
        fun changePage(fragment: Fragment)
        fun closeApplicaton()

    }
}