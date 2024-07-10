package ru.kpfu.itis.summerlab.team8.cookup.favoriteList

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.RequestManager
import ru.kpfu.itis.summerlab.team8.cookup.R
import ru.kpfu.itis.summerlab.team8.cookup.recipe.Recipe
import ru.kpfu.itis.summerlab.team8.cookup.databinding.ItemRecipeFeedBinding

class FavoriteHolder(
    private val binding: ItemRecipeFeedBinding,
    private val glide: RequestManager,
    private val onClick: (Recipe) -> Unit,
) : ViewHolder(binding.root) {

    fun onBind(recipe: Recipe) {
        binding.run {
            textViewTitle.text = recipe.name
            textViewDescription.text = recipe.description
            glide
                .load(recipe.urlImage)
                .error(R.drawable.logo_peach)
                .placeholder(R.drawable.ic_autorenew)
                .into(imageViewRecipe)

            root.setOnClickListener {
                onClick.invoke(recipe)
            }
        }
    }
}