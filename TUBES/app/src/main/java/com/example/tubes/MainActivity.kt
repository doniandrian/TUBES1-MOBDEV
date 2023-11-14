package com.example.tubes

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import com.example.tubes.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(){
    private lateinit var binding : ActivityMainBinding
    private val fragmentManager: FragmentManager = supportFragmentManager
    private val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
    lateinit var drawer : DrawerLayout
    lateinit var toolbar : Toolbar
    var statusdate by Delegates.notNull<Boolean>()
    var statusfontsize : String = "medium"
    var statusBeforeFontSize :String = "medium"
    var textSizeFactor = 30
    var sum : Int = 0
    private lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //set dark mode kalau di nyalakan
        if(PenyimpananSetting(this).isDarkModeEnabled()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        statusdate = PenyimpananSetting(this).isDisplayDateTimeEnabled()

        setContentView(binding.root)

        toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        drawer = binding.drawerLayout

        val abdt = ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer)
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

    fun changePage(fragment: Fragment) {
        val settingFragment : SettingFragment
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)

        fragmentTransaction.commit()
    }




    fun changeFontSize(size: String) {


        if(statusBeforeFontSize=="large" && size=="large"){

        }
        else if(statusBeforeFontSize=="medium"&& size=="medium"){

        }else if(statusBeforeFontSize=="small"&& size=="small"){

        }
        else if (statusBeforeFontSize=="large" && size=="medium"){
            textSizeFactor = -10
            updateTextSizesRecursive(findViewById<ViewGroup>(android.R.id.content))
        }
        else if (statusBeforeFontSize=="large"&& size=="small"){

            textSizeFactor = -20
            updateTextSizesRecursive(findViewById<ViewGroup>(android.R.id.content))
        }
        else if (statusBeforeFontSize=="medium"&& size=="large"){

            textSizeFactor = 10
            updateTextSizesRecursive(findViewById<ViewGroup>(android.R.id.content))
        }else if(statusBeforeFontSize=="medium"&& size=="small"){

            textSizeFactor = -10
            updateTextSizesRecursive(findViewById<ViewGroup>(android.R.id.content))
        }else if(statusBeforeFontSize=="small"&& size=="large"){

            textSizeFactor = 20
            updateTextSizesRecursive(findViewById<ViewGroup>(android.R.id.content))
        }else if(statusBeforeFontSize=="small"&& size=="medium"){

            textSizeFactor = 10
            updateTextSizesRecursive(findViewById<ViewGroup>(android.R.id.content))
        }

        statusBeforeFontSize = size
        Log.d(statusBeforeFontSize,"status skrng")
    }




    fun updateTextSizesRecursive(view: View) {
        if (view is TextView) {
            val newSize = view.textSize + textSizeFactor
            view.setTextSize(TypedValue.COMPLEX_UNIT_PX, newSize)
        } else if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                updateTextSizesRecursive(view.getChildAt(i))
            }
        }
    }
    fun changeDisplayTime(status: Boolean) {
        statusdate = status
    }


    fun closeApplicaton(){
        this.moveTaskToBack(true)
        finish()
    }
}