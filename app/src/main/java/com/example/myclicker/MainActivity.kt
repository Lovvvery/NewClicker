package com.example.myclicker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val prefs = getSharedPreferences("click_save",MODE_PRIVATE)
        var money = prefs.getInt("money", 0)
        var moneyPlus = 1

        val btn: Button = findViewById(R.id.buttonClick)
        val score: TextView = findViewById(R.id.TextMoney)

        score.text = "$money $".toString()

        btn.setOnClickListener {
            money += moneyPlus
            score.text = "$money $".toString()
            prefs.edit().putInt("money", money).apply()
        }
    }
    fun shopButton(view: View){
        val intent = Intent(this, ShopActivity::class.java)
        startActivity(intent)
    }
}