package com.example.myclicker

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences

    private var money = 0
    private var moneyPlus = 1
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

        prefs = getSharedPreferences(Constants.CLICK_SAVE, MODE_PRIVATE)
        scoreTextView = findViewById<TextView>(R.id.scoreTextView)
        loadData()
    }

    override fun onStop() {
        super.onStop()
        saveData()
    }

    private fun loadData() {
        money = prefs.getInt(Constants.MONEY_KEY, 0)
        moneyPlus = prefs.getInt(Constants.MONEY_PLUS_KEY, 1)

        scoreTextView.text = "${money}$"
    }

    private fun saveData() {
        prefs.edit {
            putInt(Constants.MONEY_KEY, money)
            putInt(Constants.MONEY_PLUS_KEY, moneyPlus)
        }
    }

    fun onButtonClick(view: View) {
        val imageButton = view as ImageButton
        money += moneyPlus
        scoreTextView.text = "${money}$"

        if (money >= 100) {
            imageButton.setImageResource(R.drawable.lvl2)
        }
        if (money >= 400){
            imageButton.setImageResource(R.drawable.lvl3)
        }
    }

    fun shopButton(view: View) {
        saveData()

        val intent = Intent(this, ShopActivity::class.java)
        startActivity(intent)
    }
}