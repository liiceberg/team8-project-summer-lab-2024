package ru.kpfu.itis.summerlab.team8.cookup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
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
            val list = arguments?.getStringArrayList("list")?.toSet()
            adapter = RecipeAdapter(
                list = searchRecipe(list!!),
                glide = Glide.with(this@RecipeListFragment),
                onClick = {
                    val bundle = Bundle()
                    bundle.apply {
                        bundle.putLong("id", it.id);
                    }
                    findNavController().navigate(
                        R.id.action_recipeListFragment_to_recipeInfoFragment,
                        bundle
                    )

                }
            )
            rvRecipe.adapter = adapter
            rvRecipe.layoutManager = GridLayoutManager(requireContext(), 2)

            topAppBarMenu.setNavigationOnClickListener {
                findNavController().navigateUp()
            }

        }
    }

    private fun searchRecipe(list: Set<String>): List<Recipe> {
        val ans = ArrayList<Recipe>()
        for (item in RecipeRepository.recipes) {
            val ingredients = item.ingredients.split(",").map {i -> i.trim() }.toSet()
            if (ingredients == list) {
                ans.add(item)
            }
        }
        return ans
    }

}