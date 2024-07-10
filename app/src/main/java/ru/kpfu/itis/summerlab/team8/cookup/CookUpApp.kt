package ru.kpfu.itis.summerlab.team8.cookup

import android.app.Application
import ru.kpfu.itis.summerlab.team8.cookup.productList.ProductsRepository
import ru.kpfu.itis.summerlab.team8.cookup.di.ServiceLocator


class CookUpApp : Application() {

    override fun onCreate() {
        super.onCreate()
        ServiceLocator.initDatabase(this)
        ProductsRepository.checkAndCreateFile(this)
        val directory = this.filesDir

        // Get all files in the directory
        val files = directory.listFiles()

        if (files != null && files.isNotEmpty()) {
            for (file in files) {
                println("File: ${file.name}")
            }
        } else {
            println("No files found in the directory.")
        }
    }
}