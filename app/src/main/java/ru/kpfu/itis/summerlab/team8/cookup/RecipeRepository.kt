package ru.kpfu.itis.summerlab.team8.cookup

object RecipeRepository {
    val recipes: List<Recipe> = listOf(
        Recipe(
            id = 0,
            name = "Carbonara",
            urlImage = "https://media.leverans.ru/product_images_inactive/novosibirsk/beerman/7f35f4a7349215e9570f73423328e0c0.jpg",
            description = "52! Да Здравствует Санкт-Петербург",
            recipe = "sdf",
            listOfIngredients = hashSetOf("tomato", "juice"),
            instructions = "With your feet on the air and your head on the ground\n" +
                    "Try this trick and spin it, yeah (Yeah)\n" +
                    "Your head will collapse if there's nothing in it\n" +
                    "And you'll ask yourself\n" +
                    "\n" +
                    "Where is my mind?\n" +
                    "Where is my mind?\n" +
                    "Where is my mind?\n" +
                    "Way out in the water\n" +
                    "See it swimming\n" +
                    "\n" +
                    "I was swimming in the Caribbean\n" +
                    "Animals were hiding behind the rock\n" +
                    "Except the little fish bumped into me\n" +
                    "I swear he was trying to talk to me, to me, to me\n" +
                    "\n" +
                    "Where is my mind?\n" +
                    "Where is my mind?\n" +
                    "Where is my mind?\n" +
                    "Way out in the water\n" +
                    "See it swimming"
        ),
        Recipe(
            id = 1,
            name = "Caesar",
            urlImage = "https://avatars.mds.yandex.net/get-entity_search/117753/846435852/S600xU_2x",
            description = "Самый лучший салат в домашней столовой",
            recipe = "Яйцааааааааа и сыр",
            listOfIngredients = hashSetOf("egg", "cheese"),
            instructions = "Шо здесь думать? Берешь и готовишь!"
        ),
        Recipe(
            id = 2,
            name = "Caesar",
            urlImage = "",
            description = "asd",
            recipe = "asd",
            listOfIngredients = hashSetOf("egg", "cheese"),
            instructions = "asd"
        ),
    )
}