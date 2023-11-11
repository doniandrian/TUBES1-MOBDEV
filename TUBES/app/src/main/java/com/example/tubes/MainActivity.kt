package com.example.tubes

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.RelativeLayout
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tubes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
    private lateinit var binding : ActivityMainBinding
    private val fragmentManager: FragmentManager = supportFragmentManager
    private val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
    lateinit var drawer : DrawerLayout
    lateinit var toolbar : Toolbar
    private var textSizeFactor = 1.0f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
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
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }



    fun changeFontSize(size: Float){
        //change font size
        textSizeFactor *= size

        // Store textSizeFactor in SharedPreferences
        val sharedPref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putFloat("textSizeFactor", textSizeFactor)
        editor.apply()
        // Re-apply the text size to all views
        updateTextSizesRecursive(findViewById<ViewGroup>(android.R.id.content))



    }
    fun updateTextSizesRecursive(view: View) {
        if (view is TextView) {
            val newSize = view.textSize / textSizeFactor
            view.setTextSize(TypedValue.COMPLEX_UNIT_PX, newSize)
        } else if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                updateTextSizesRecursive(view.getChildAt(i))
            }
        }
    }


    fun closeApplicaton(){
        this.moveTaskToBack(true)
        finish()
    }
}