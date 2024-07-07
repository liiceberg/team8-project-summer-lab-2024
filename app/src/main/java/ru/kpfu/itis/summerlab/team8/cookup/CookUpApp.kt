package ru.kpfu.itis.summerlab.team8.cookup

import android.app.Application
import androidx.lifecycle.lifecycleScope
import ru.kpfu.itis.summerlab.team8.cookup.di.ServiceLocator

class CookUpApp : Application() {

    override fun onCreate() {
        super.onCreate()
        ServiceLocator.initDatabase(this)
    }
}