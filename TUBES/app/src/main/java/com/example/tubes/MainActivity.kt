package com.example.tubes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    private val fragmentManager: FragmentManager = supportFragmentManager
    private val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)

        val abdt = ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer)
        drawer.addDrawerListener(abdt)
        abdt.syncState()

        fragmentTransaction.replace(R.id.fragment_container, MainFragment())
        fragmentTransaction.commit()

        val leftDrawer = findViewById<RelativeLayout>(R.id.left_drawer)
        val leftFragment = LeftFragment()
        leftDrawer.setOnClickListener {
            changePage(leftFragment)
            drawer.closeDrawer(GravityCompat.START)
        }

        val textHome = findViewById<TextView>(R.id.text_home)
        textHome.setOnClickListener {
            changePage(DetailFragment())
            drawer.closeDrawer(GravityCompat.START)
        }

        val textExit = findViewById<TextView>(R.id.text_exit)
        textExit.setOnClickListener {
            moveTaskToBack(true)
            finish()
        }
    }

    fun changePage(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}