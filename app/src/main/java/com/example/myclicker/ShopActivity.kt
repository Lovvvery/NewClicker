package com.example.myclicker

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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
        var prefs= getSharedPreferences("click_save", MODE_PRIVATE)
        var money = prefs.getInt("money", 0)
        var moneyPlus = prefs.getInt("moneyPlus",1)

        val btn: Button=findViewById(R.id.buttonBuy)

        btn.setOnClickListener {
            var money = prefs.getInt("money",0)
            if(money >= 100){
                money -= 100
                moneyPlus = 10
                prefs.edit()
                    .putInt("money", money)
                    .putInt("moneyPlus", 10)
                    .apply()
                Toast.makeText(this,"Покупка успешна!", Toast.LENGTH_SHORT).show()

            }
            else{
                Toast.makeText(this, "Недостаточно средств", Toast.LENGTH_SHORT).show()
            }
        }

    }


    fun backButton(view: View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


}