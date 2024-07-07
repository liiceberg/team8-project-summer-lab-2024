package ru.kpfu.itis.summerlab.team8.cookup.RecipeFeed

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import ru.kpfu.itis.summerlab.team8.cookup.R
import ru.kpfu.itis.summerlab.team8.cookup.Recipe
import ru.kpfu.itis.summerlab.team8.cookup.databinding.FragmentFeedBinding


class FeedFragment : Fragment(R.layout.fragment_feed) {

    private lateinit var allRecipes: List<Recipe>

    private var binding: FragmentFeedBinding? = null
    private var adapter: RecipeAdapter? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFeedBinding.bind(view)
        initAdapter()
        allRecipes = RecipeRepository.getAllRecipes()
        binding?.run {
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
        val filteredRecipes = allRecipes.filter { recipe ->
            recipe.name.contains(query, ignoreCase = true)
        }
        adapter?.updateData(filteredRecipes)
    }


    private fun initAdapter() {
        adapter = RecipeAdapter(RecipeRepository.recipes, Glide.with(this)) { id ->

        }
        binding?.run {
            rvRecipes.adapter = adapter
            rvRecipes.layoutManager = LinearLayoutManager(requireContext())
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}