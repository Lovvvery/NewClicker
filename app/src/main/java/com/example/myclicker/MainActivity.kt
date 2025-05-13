package com.example.myclicker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var money=0
    private var moneyPlus=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val prefs = getSharedPreferences("click_save", MODE_PRIVATE)
        money= prefs.getInt("money", 0)

        val btn: ImageButton = findViewById(R.id.buttonClick)
        val score: TextView = findViewById(R.id.TextMoney)

        score.text = "$money $"

        btn.setOnClickListener {
            money += moneyPlus
            score.text = "$money $".toString()

        }


    }
    override fun onStop() {
        super.onStop()
        val prefs = getSharedPreferences("click_save",MODE_PRIVATE)
        prefs.edit().putInt("money", money).apply()

    }
    fun shopButton(view: View){
        val intent = Intent(this, ShopActivity::class.java)
        startActivity(intent)
    }
}