package com.example.myclicker.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.myclicker.ClickerApp.Companion.profile
import com.example.myclicker.R
import com.example.myclicker.data.Constants
import com.example.myclicker.data.Database
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var scoreTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val prefs = getSharedPreferences(Constants.CLICK_SAVE, MODE_PRIVATE)
        Database.init(prefs)
        scoreTextView = findViewById<TextView>(R.id.scoreTextView)

        autoIncrementer()
    }

    private fun autoIncrementer() {
        lifecycleScope.launch {
            while (isActive) {
                profile.money += profile.autoIncrement
                updateScoreTextView()
                delay(1000)
            }
        }
    }

    private fun updateScoreTextView() {
        runOnUiThread {
            scoreTextView.text = "${profile.money}$"
        }
    }

    override fun onStart() {
        super.onStart()
        Database.loadData(profile)
        updateScoreTextView()
    }

    override fun onPause() {
        super.onPause()
        Database.saveData(profile)
    }

    fun onButtonClick(view: View) {
        val imageButton = view as ImageButton
        profile.money += profile.clickIncrement
        updateScoreTextView()

        when {
            profile.money >= 100 -> imageButton.setImageResource(R.drawable.lvl2)
            profile.money >= 400 -> imageButton.setImageResource(R.drawable.lvl3)
        }
    }

    fun shopButton(view: View) {
        Database.saveData(profile)

        val intent = Intent(this, ShopActivity::class.java)
        startActivity(intent)
    }
}