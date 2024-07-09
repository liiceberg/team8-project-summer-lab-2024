package ru.kpfu.itis.summerlab.team8.cookup

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.RequestManager
import ru.kpfu.itis.summerlab.team8.cookup.databinding.ItemRecipeBinding

class RecipeHolder(
    private val binding: ItemRecipeBinding,
    private val glide: RequestManager,
    private val onClick: (Recipe) -> Unit,
) : ViewHolder(binding.root) {

    fun onBind(recipe: Recipe) {
        binding.run {
            tvNameRecipe.text = recipe.name

            glide
                .load(recipe.urlImage)
                .error(R.drawable.logo_peach)
                .placeholder(R.drawable.ic_autorenew)
                .into(ivPhoto)

            root.setOnClickListener {
                onClick.invoke(recipe)
            }
        }
    }
}