package ru.kpfu.itis.summerlab.team8.cookup

data class Recipe(
    val id: Long,
    val name: String,
    val urlImage: String,
    val description: String,
    val listOfIngredients: Set<String>,
    val instructions: String = "",
    var favorite: Boolean = false
)