package com.example.rolethedice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.rolethedice.data.AppLifecycleObserver
import com.example.rolethedice.data.LOG_TAG
import com.example.rolethedice.ui.mainViewModel
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var message: TextView
    private lateinit var viewModel: AndroidViewModel
    private val drawerLayout by lazy {
        findViewById<DrawerLayout>(R.id.drawer_layout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_nav)

        message = findViewById(R.id.text)

        // init view model
        viewModel = ViewModelProvider(this).get(mainViewModel::class.java)
        (viewModel as mainViewModel).resultData.observe(
            this,
            Observer {
                // show data on screen
                message.text= it.toString()
                //Log.i(LOG_TAG, it.toString())
            },
        )

        // add nav toggle
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.open_drawer, R.string.close_drawer,
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        // add back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // change back button
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_check_24)

        // track life cycle
        lifecycle.addObserver(AppLifecycleObserver())

        // show nav menu and click listener
        val navMenu = findViewById<NavigationView>(R.id.nav_view)
        navMenu.setNavigationItemSelectedListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.role_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> {
                shareText()
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareText() {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Iam here")
            type = "text/plain"
        }
        startActivity(intent)
    }

    // click items on menu
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        //close drawable menu
        drawerLayout.closeDrawer(GravityCompat.START)
        // click on menu items
        when (item.itemId) {
            R.id.settings -> {
                Toast.makeText(this, "Settings", Toast.LENGTH_LONG).show()
            }
            R.id.about -> {
                Toast.makeText(this, "About", Toast.LENGTH_LONG).show()
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }
}