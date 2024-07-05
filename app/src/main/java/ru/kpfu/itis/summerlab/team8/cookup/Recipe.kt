package ru.kpfu.itis.summerlab.team8.cookup

data class Recipe(
    val id: Long,
    val name: String,
    val urlImage: String,
    val description: String,
    val recipe: String,
    val ListOfIngredients: Set<String>
)