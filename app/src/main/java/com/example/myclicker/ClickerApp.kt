package com.example.myclicker

import android.app.Application
import com.example.myclicker.data.entity.Profile

class ClickerApp : Application() {
    companion object {
        var profile = Profile()
    }
}