package com.example.tubes.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.tubes.R
import com.example.tubes.databinding.ActivityMainBinding
import com.example.tubes.model.PenyimpananSetting
import com.example.tubes.presenter.MainPresenter
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), IMainFragment.Ui{
    private lateinit var binding : ActivityMainBinding
    private val fragmentManager: FragmentManager = supportFragmentManager
    private val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
    lateinit var penyimpananSetting: PenyimpananSetting
    private lateinit var mainPresenter: MainPresenter
    lateinit var drawer : DrawerLayout
    lateinit var toolbar : Toolbar
    var statusdate by Delegates.notNull<Boolean>()
    var statusfontsize : String = "medium"
    var statusBeforeFontSize :String = "medium"
    var textSizeFactor = 30
    var sum : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        penyimpananSetting = PenyimpananSetting(this)

        statusdate = penyimpananSetting.isDisplayDateTimeEnabled()

        mainPresenter = MainPresenter()

        mainPresenter.darkModeEnable(this)

        setContentView(binding.root)

        toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        drawer = binding.drawerLayout

        val abdt = ActionBarDrawerToggle(this, drawer, toolbar,
            R.string.openDrawer,
            R.string.closeDrawer
        )
        drawer.addDrawerListener(abdt)
        abdt.syncState()

        fragmentTransaction.replace(R.id.fragment_container, MainFragment())
        fragmentTransaction.add(binding.leftDrawer .id, LeftFragment())
        fragmentTransaction.hide(LeftFragment())
        fragmentTransaction.commit()

        drawer.addDrawerListener(object : androidx.drawerlayout.widget.DrawerLayout.DrawerListener{
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                return
            }

            override fun onDrawerOpened(drawerView: View) {
                supportFragmentManager.beginTransaction().apply{
                    show(LeftFragment())
                    addToBackStack(null)
                    commit()
                }
            }

            override fun onDrawerClosed(drawerView: View) {
                supportFragmentManager.beginTransaction().apply{
                    hide(LeftFragment())
                    addToBackStack(null)
                    commit()
                }
            }

            override fun onDrawerStateChanged(newState: Int) {
                return
            }
        })
    }

    override fun changePage(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun closeApplicaton(){
        mainPresenter.closeApplicaton(this)
    }
}