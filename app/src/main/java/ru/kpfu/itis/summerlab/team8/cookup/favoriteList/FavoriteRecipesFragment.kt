package ru.kpfu.itis.summerlab.team8.cookup.favoriteList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import ru.kpfu.itis.summerlab.team8.cookup.R
import ru.kpfu.itis.summerlab.team8.cookup.recipe.Recipe
import ru.kpfu.itis.summerlab.team8.cookup.databinding.FragmentFavoriteRecipesBinding
import ru.kpfu.itis.summerlab.team8.cookup.recipe.RecipeRepository

class FavoriteRecipesFragment : Fragment(R.layout.fragment_favorite_recipes) {

    private lateinit var binding: FragmentFavoriteRecipesBinding
    private var adapter: FavoriteAdapter? = null
    private lateinit var favoriteRecipes: List<Recipe>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoriteRecipesBinding.bind(view)

        initAdapter()

        favoriteRecipes =
            RecipeRepository.recipes.filter { it.isFavourite }

        binding.run {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let { performSearch(it) }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let { performSearch(it) }
                    return true
                }

            })
        }
    }

    private fun performSearch(query: String) {
        val filteredRecipes = favoriteRecipes.filter { recipe ->
            recipe.name.contains(query, ignoreCase = true)
        }
        adapter?.updateData(filteredRecipes)
    }

    private fun initAdapter() {
        binding.run {
            adapter = FavoriteAdapter(
                list = RecipeRepository.recipes.filter { it.isFavourite },
                glide = Glide.with(this@FavoriteRecipesFragment),
                onClick = {
                    val bundle = Bundle()
                    bundle.apply {
                        bundle.putLong("id", it.id)
                    }
                    findNavController().navigate(
                        R.id.action_favoriteRecipesFragment_to_recipeInfoFragment,
                        bundle
                    )
                }
            )
            rvRecipes.adapter = adapter
            rvRecipes.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}