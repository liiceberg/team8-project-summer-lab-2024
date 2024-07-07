package ru.kpfu.itis.summerlab.team8.cookup.RecipeFeed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import ru.kpfu.itis.summerlab.team8.cookup.Recipe
import ru.kpfu.itis.summerlab.team8.cookup.databinding.ItemRecipeFeedBinding

class RecipeAdapter(
    private var list: List<Recipe>,
    private val glide: RequestManager,
    private val click: (Long) -> Unit
) : RecyclerView.Adapter<RecipeHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeHolder = RecipeHolder(
        glide,
        ItemRecipeFeedBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ), click
    )

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        holder.onBind(list[position])
    }

    fun updateData(newRecipes: List<Recipe>) {
        list = newRecipes
        notifyDataSetChanged()
    }


}