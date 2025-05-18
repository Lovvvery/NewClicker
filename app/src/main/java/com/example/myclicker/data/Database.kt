package com.example.myclicker.data

import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.myclicker.data.entity.Profile

object Database {
    private lateinit var prefs: SharedPreferences

    fun init(prefs: SharedPreferences) {
        this.prefs = prefs
    }

    fun loadData(profile: Profile) {
        profile.apply {
            this.money = prefs.getInt(Constants.MONEY_KEY, 0)
            this.clickIncrement = prefs.getInt(Constants.MONEY_CLICK_INCREMENT_KEY, 1)
            this.autoIncrement = prefs.getInt(Constants.AUTO_INCREMENT_KEY, 0)
        }
    }

    fun saveData(profile: Profile) {
        prefs.edit {
            putInt(Constants.MONEY_KEY, profile.money)
            putInt(Constants.MONEY_CLICK_INCREMENT_KEY, profile.clickIncrement)
            putInt(Constants.AUTO_INCREMENT_KEY, profile.autoIncrement)
        }
    }
}