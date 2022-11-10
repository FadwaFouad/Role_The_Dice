package com.example.rolethedice

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
      private lateinit var text :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // add back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // change back button
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_check_24)

        setContentView(R.layout.activity_main)
        // track life cycle
        lifecycle.addObserver(AppLifecycleObserver())

        text = findViewById(R.id.text)
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
            action=  Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Iam here")
            type= "text/plain"
        }
        startActivity(intent)
    }
}