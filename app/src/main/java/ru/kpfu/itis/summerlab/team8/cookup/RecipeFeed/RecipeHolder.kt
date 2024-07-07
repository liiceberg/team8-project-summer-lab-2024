package ru.kpfu.itis.summerlab.team8.cookup.RecipeFeed

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import ru.kpfu.itis.summerlab.team8.cookup.Recipe
import ru.kpfu.itis.summerlab.team8.cookup.databinding.ItemRecipeFeedBinding

class RecipeHolder(
    private val glide: RequestManager,
    private val binding: ItemRecipeFeedBinding,
    private val click: (Long) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(recipe: Recipe) {
        binding.run {
            textViewTitle.text = recipe.name
            textViewDescription.text = recipe.description
            glide.load(recipe.urlImage).into(imageViewRecipe)
            root.setOnClickListener { click(recipe.id) }
        }
    }

    fun bind(recipe: Recipe) {
        binding.run {
            textViewTitle.text = recipe.name
            textViewDescription.text = recipe.description
            glide.load(recipe.urlImage).into(imageViewRecipe)
            root.setOnClickListener { click(recipe.id) }
        }
    }
}