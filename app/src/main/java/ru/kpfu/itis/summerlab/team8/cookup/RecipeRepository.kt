package ru.kpfu.itis.summerlab.team8.cookup

object RecipeRepository {
    val recipes: List<Recipe> = listOf(
        Recipe(
            id = 0,
            name = "Carbonara",
            urlImage = "https://media.leverans.ru/product_images_inactive/novosibirsk/beerman/7f35f4a7349215e9570f73423328e0c0.jpg",
            description = "",
            recipe = "",
            ListOfIngredients = hashSetOf("Tomato", "Juice")
        ),
        Recipe(
            id = 1,
            name = "Caesar",
            urlImage = "https://avatars.mds.yandex.net/get-entity_search/117753/846435852/S600xU_2x",
            description = "",
            recipe = "",
            ListOfIngredients = hashSetOf("Egg", "Cheese")
        ),
    )
}