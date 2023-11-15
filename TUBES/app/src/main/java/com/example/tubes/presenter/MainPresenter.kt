package com.example.tubes.presenter

import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.tubes.R
import com.example.tubes.model.DetailItem
import com.example.tubes.model.PenyimpananSetting
import com.example.tubes.view.IMainFragment
import com.example.tubes.view.MainActivity
import java.text.SimpleDateFormat

import java.util.*

class MainPresenter: IMainFragment.Presenter{
    override fun getCurrentDate(): String {
        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm aaa", Locale.getDefault())
        return dateFormat.format(currentDate)
    }

    override fun updateDetail(detailList: MutableList<DetailItem>, desc: String, story: String, position: Int){
        detailList[position].desc = desc
        detailList[position].story = story
    }

    override fun darkModeEnable(activity: MainActivity){
        if(activity.penyimpananSetting.isDarkModeEnabled()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    override fun closeApplicaton(activity: MainActivity){
        activity.moveTaskToBack(true)
        activity.finish()
    }
}

//referensi:
//https://stackoverflow.com/questions/47006254/how-to-get-current-local-date-and-time-in-kotlin