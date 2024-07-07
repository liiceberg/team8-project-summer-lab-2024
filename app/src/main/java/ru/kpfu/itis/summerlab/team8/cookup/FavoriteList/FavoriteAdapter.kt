package ru.kpfu.itis.summerlab.team8.cookup.FavoriteList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import ru.kpfu.itis.summerlab.team8.cookup.Recipe
import ru.kpfu.itis.summerlab.team8.cookup.databinding.ItemRecipeFeedBinding

class FavoriteAdapter(
    private var list: List<Recipe>,
    private val glide: RequestManager,
    private val onClick: (Recipe) -> Unit,
) : RecyclerView.Adapter<FavoriteHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteHolder = FavoriteHolder(
        binding = ItemRecipeFeedBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        glide = glide,
        onClick = onClick,
    )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FavoriteHolder, position: Int) {
        holder.onBind(list[position])
    }

    fun updateData(newRecipes: List<Recipe>) {
        list = newRecipes
        notifyDataSetChanged()
    }

}