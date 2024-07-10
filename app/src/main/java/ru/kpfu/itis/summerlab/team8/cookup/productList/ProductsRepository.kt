package ru.kpfu.itis.summerlab.team8.cookup.productList

import android.content.Context
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import java.io.File
import java.io.IOException


object ProductsRepository {
    var products: MutableList<Product> = mutableListOf()
    private var file : File? = null


    fun checkAndCreateFile(context: Context) {
        val file = File(context.filesDir, "products.csv")
        this.file = file

        if (!file.exists()) {
            try {
                file.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    fun setProducts(){
        csvReader().open(file!!) {
            readAllAsSequence().forEach { row: List<String> ->
                if(row.size > 1) products.add(Product(row[0], row[1].toBoolean()))
            }
        }
    }

    fun add(products: List<Product>){
        this.products.addAll(products)
        csvWriter().writeAll(products.map { product -> listOf(product.name, product.isChecked) }, file!!, append = true)
    }

    fun add(product: Product){
        products.add(product)
        csvWriter().open(file!!, append = true){
            writeRow(product.name, product.isChecked)
        }
    }

    fun update(){
        csvWriter().writeAll(products.map { product -> listOf(product.name, product.isChecked.toString()) }, file!!)
    }

    fun clear(context : Context){
        products.clear()
        val file = File(context.filesDir, "products.csv")
        file.delete()
        checkAndCreateFile(context)
    }
}