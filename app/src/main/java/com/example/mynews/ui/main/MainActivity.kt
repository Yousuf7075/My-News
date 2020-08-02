package com.example.mynews.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.mynews.R
import com.example.mynews.databinding.ActivityMainBinding
import com.example.mynews.ui.home.HomeFragment
import com.example.mynews.ui.science.ScienceFragment
import com.example.mynews.ui.world.WorldFragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mMainBinding: ActivityMainBinding
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mMainBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, mMainBinding.drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        mMainBinding.navView.setNavigationItemSelectedListener(this)
        mMainBinding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null){
            openFragment(HomeFragment())
            setToolbarTitle("Home")
            mMainBinding.navView.setCheckedItem(R.id.nav_home)
        }
    }

    override fun onBackPressed() {
        if (mMainBinding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            mMainBinding.drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home -> {
                openFragment(HomeFragment())
                setToolbarTitle("Home")
            }
            R.id.nav_world -> {
                openFragment(WorldFragment())
                setToolbarTitle("World")
            }
            R.id.nav_science -> {
                openFragment(ScienceFragment())
                setToolbarTitle("Science")
            }
        }
        mMainBinding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun openFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment).commit()
    }

    private fun setToolbarTitle(title: String){
        toolbar.title = title
    }

}

