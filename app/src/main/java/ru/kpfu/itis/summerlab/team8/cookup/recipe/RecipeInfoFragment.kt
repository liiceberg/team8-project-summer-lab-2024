package ru.kpfu.itis.summerlab.team8.cookup.recipe

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import ru.kpfu.itis.summerlab.team8.cookup.productList.Product
import ru.kpfu.itis.summerlab.team8.cookup.productList.ProductsRepository
import ru.kpfu.itis.summerlab.team8.cookup.R
import ru.kpfu.itis.summerlab.team8.cookup.databinding.FragmentRecipeInfoBinding
import ru.kpfu.itis.summerlab.team8.cookup.di.ServiceLocator

class RecipeInfoFragment : Fragment(R.layout.fragment_recipe_info) {
    private lateinit var binding: FragmentRecipeInfoBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRecipeInfoBinding.bind(view)
        val id = arguments?.getLong("id")
        binding.run {
            val recipe = RecipeRepository.recipes.find { it.id == id }

            if (recipe != null) {
                title.text = recipe.name
                description.text = recipe.description
                ingredients.text =
                    recipe.ingredients.split(",").joinToString("\n") { ing -> "-${ing.trim()}" }
                instructions.text = recipe.instructions
                Glide.with(view)
                    .load(recipe.urlImage)
                    .error(R.drawable.logo_peach)
                    .placeholder(R.drawable.ic_autorenew)
                    .into(recipeImage)
                println(id)
            }

            if(recipe?.isFavourite == true) {
                fabLike.setImageResource(R.drawable.ic_like_filled)
            } else {
                fabLike.setImageResource(R.drawable.ic_like)
            }

            fabBack.setOnClickListener {
                findNavController().navigateUp()
            }

            fabLike.setOnClickListener {
                if(recipe?.isFavourite == true) {
                    fabLike.setImageResource(R.drawable.ic_like)
                    recipe.isFavourite = false
                    Snackbar.make(view, "${recipe.name} deleted from favorite", Snackbar.LENGTH_SHORT).show()
                } else {
                    fabLike.setImageResource(R.drawable.ic_like_filled)
                    recipe?.isFavourite = true
                    Snackbar.make(view, "${recipe?.name} added to favorite", Snackbar.LENGTH_SHORT).show()
                }

                lifecycleScope.launch {
                    ServiceLocator.getDbInstance().recipeDao().update(recipe!!)
                }
            }

            addToProductsButton.setOnClickListener{
                ProductsRepository.add(ingredients.text.split("\n").filter { str -> str != "" }.map { product -> Product(product.replace("-", "").trim(), false)})
                Snackbar.make(view, "ingredients added to list", Snackbar.LENGTH_SHORT).show()
            }

        }
    }
}