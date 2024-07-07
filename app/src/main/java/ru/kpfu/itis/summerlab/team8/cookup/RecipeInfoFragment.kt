package ru.kpfu.itis.summerlab.team8.cookup

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
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
                ingredients.text = recipe.ingredients.split(",").joinToString("\n")
                instructions.text = recipe.instructions
                Glide.with(view)
                    .load(recipe.urlImage)
                    .error(R.drawable.ic_android)
                    .placeholder(R.drawable.ic_autorenew)
                    .into(recipeImage)
                println(id)
            }

            fabBack.setOnClickListener {
                findNavController().navigateUp()
            }

        }
    }
}