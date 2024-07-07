package ru.kpfu.itis.summerlab.team8.cookup

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import ru.kpfu.itis.summerlab.team8.cookup.databinding.FragmentRecipeInfoBinding

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
                ingredients.text = convertSetToString(recipe.listOfIngredients)
                instructions.text = recipe.instructions
                Glide.with(view)
                    .load(recipe.urlImage)
                    .error(R.drawable.ic_android)
                    .placeholder(R.drawable.ic_autorenew)
                    .into(recipeImage)
                println(id)
            }

            if(recipe?.favorite == true) {
                fabLike.setImageResource(R.drawable.ic_like_filled)
            } else {
                fabLike.setImageResource(R.drawable.ic_like)
            }

            fabBack.setOnClickListener {
                findNavController().navigateUp()
            }

            fabLike.setOnClickListener {
                if(recipe?.favorite == true) {
                    fabLike.setImageResource(R.drawable.ic_like)
                    recipe.favorite = false
                    Snackbar.make(view, "${recipe?.name} deleted from favorite", Snackbar.LENGTH_SHORT).show()
                } else {
                    fabLike.setImageResource(R.drawable.ic_like_filled)
                    recipe?.favorite = true
                    Snackbar.make(view, "${recipe?.name} added to favorite", Snackbar.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun convertSetToString(list: Set<String>): String {
        val ans = StringBuilder()
        for (item in list) {
            ans.append(item + "\n")
        }
        return ans.toString()
    }
}