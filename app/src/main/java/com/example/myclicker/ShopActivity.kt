package com.example.myclicker

import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.collection.MutableIntList
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.logging.Handler

class ShopActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    private var money = 0
    private var moneyPlus = 10
    private var moneyPlusPlus = 50



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_shop)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        prefs = getSharedPreferences(Constants.CLICK_SAVE, MODE_PRIVATE)
        money = prefs.getInt(Constants.MONEY_KEY, 0)
        moneyPlus = prefs.getInt(Constants.MONEY_PLUS_KEY, 1)

        val btn: Button = findViewById(R.id.buttonBuy)

        btn.setOnClickListener {
            var money = prefs.getInt(Constants.MONEY_KEY,0)
            if (money >= 100) {
                money -= 100
                moneyPlus = 10
                prefs.edit {
                    putInt(Constants.MONEY_KEY, money)
                    putInt(Constants.MONEY_PLUS_KEY, 10)
                }

                Toast.makeText(this, "Покупка успешна!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Недостаточно средств", Toast.LENGTH_SHORT).show()
            }
        }
        val btn2: Button = findViewById(R.id.buttonBuy2)

        btn2.setOnClickListener {
            var money = prefs.getInt(Constants.MONEY_KEY,0)
            if(money >= 1000){
                money -= 1000
                moneyPlusPlus = 50
                prefs.edit{
                    putInt(Constants.MONEY_KEY,money)
                    putInt(Constants.MONEY_PLUS_KEY,50)
                }
                Toast.makeText(this,"Покупка успешна!", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(this,"Недостаточно средств", Toast.LENGTH_SHORT).show()
        }

        //остановился тут
        var btn3: Button = findViewById(R.id.buttonBuy4)
        btn3.setOnClickListener {
            
            money -= 500
            prefs.edit{
                while (money <= 1000000000){
                    print(money)
                    money = money+1
                }

            }
            Toast.makeText(this, "Покупка успешна!", Toast.LENGTH_SHORT).show()

        }

        btn3.setOnClickListener {
            var money = prefs.getInt(Constants.MONEY_KEY,0)
        }
    }
}