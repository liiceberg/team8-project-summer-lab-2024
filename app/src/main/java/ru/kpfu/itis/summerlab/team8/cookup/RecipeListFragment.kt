package ru.kpfu.itis.summerlab.team8.cookup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import ru.kpfu.itis.summerlab.team8.cookup.databinding.FragmentRecipeListBinding

class RecipeListFragment : Fragment(R.layout.fragment_recipe_list) {

    private var binding: FragmentRecipeListBinding? = null
    private var adapter: RecipeAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRecipeListBinding.bind(view)

        initAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun initAdapter() {

        binding?.run {
            adapter = RecipeAdapter(
                list = RecipeRepository.recipes,
                glide = Glide.with(this@RecipeListFragment),
                onClick = {
                    Snackbar.make(root, it.name, Snackbar.LENGTH_SHORT).show()
                }
            )
            rvRecipe.adapter = adapter
            rvRecipe.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }
}