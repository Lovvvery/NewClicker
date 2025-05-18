package com.example.myclicker.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myclicker.ClickerApp.Companion.profile
import com.example.myclicker.R
import com.example.myclicker.data.Database

class ShopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_shop)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.buttonBuy).setOnClickListener {
            if (profile.money >= 100) {
                profile.apply {
                    money -= 100
                    clickIncrement += 10
                }

                Database.saveData(profile)
                Toast.makeText(this, "Покупка успешна!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Недостаточно средств", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.buttonBuy2).setOnClickListener {
            if (profile.money >= 1000) {
                profile.apply {
                    money -= 1000
                    clickIncrement += 50
                }

                Database.saveData(profile)
                Toast.makeText(this,"Покупка успешна!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"Недостаточно средств", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.buttonBuy4).setOnClickListener {
            if (profile.money >= 500) {
                profile.apply {
                    money -= 500
                    autoIncrement++
                }

                Database.saveData(profile)
                Toast.makeText(this, "Покупка успешна!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"Недостаточно средств", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun backButton(view: View) = finish()
}